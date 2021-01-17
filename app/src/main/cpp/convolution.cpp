//
// Created by jhahn on 1/16/21.
//
#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jfloatArray JNICALL
Java_com_freeflowgfx_convolution_implementation_Cpp_convolution(
        JNIEnv *env,
        jobject obj,
        jfloatArray signal,
        jfloatArray filter
) {
    int signalLength = env->GetArrayLength(signal);
    int filterLength = env->GetArrayLength(filter);

    jfloatArray resultArray;
    resultArray = env->NewFloatArray(signalLength);

    if (resultArray == nullptr) {
        return nullptr;
    }

    jfloat *csignal = env->GetFloatArrayElements(signal, JNI_FALSE);
    jfloat *cfilter = env->GetFloatArrayElements(filter, JNI_FALSE);

    jfloat signal_resized[signalLength + filterLength];
    for (int i = 0; i < signalLength + filterLength; i++) {
        signal_resized[i] = 0;
    }
    for (int i = 0; i < signalLength; i++) {
        signal_resized[i + filterLength / 2] = csignal[i];
    }

    jfloat result[signalLength];
    for (int i = 0; i < signalLength; i++) {
        result[i] = 0;
        for (int j = 0; j < filterLength; j++) {
            result[i] += signal_resized[i + j] * cfilter[filterLength - j - 1];
        }
    }

    env->ReleaseFloatArrayElements(signal, csignal, 0);
    env->ReleaseFloatArrayElements(filter, cfilter, 0);
    env->SetFloatArrayRegion(resultArray, 0, signalLength, result);

    return resultArray;
}

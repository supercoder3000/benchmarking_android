//
// Created by jhahn on 1/16/21.
//
#include <jni.h>
#include <string>


//void convolution(const float &signal, const float &filter) {
//
//}



extern "C"
JNIEXPORT jfloatArray JNICALL
Java_com_freeflowgfx_convolution_Cpp_convolution(
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

    jfloat result[signalLength]; // init with 0?

    for (int i = 0; i < signalLength; i++) {
        result[i] = 0;
        for (int j = 0; j < filterLength; j++) {
            int signal_index = i + j - filterLength / 2;

            if (signal_index < 0 || signal_index >= signalLength) continue;

            result[i] += csignal[signal_index] * cfilter[filterLength - j -1];
        }
    }

    env->ReleaseFloatArrayElements(signal, csignal, 0);
    env->ReleaseFloatArrayElements(filter, cfilter, 0);
    env->SetFloatArrayRegion(resultArray, 0, signalLength, result);

    return resultArray;
}

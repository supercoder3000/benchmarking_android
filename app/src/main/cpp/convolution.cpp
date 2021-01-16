//
// Created by jhahn on 1/16/21.
//
#include <jni.h>
#include <string>


//void convolution(const float &signal, const float &filter) {
//
//}



extern "C"
JNIEXPORT jint JNICALL
Java_com_freeflowgfx_convolution_Cpp_convolution(JNIEnv *env, jobject thiz, jint a, jint b) {
    return a + b;
}
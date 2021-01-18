cmake_minimum_required(VERSION 3.6.0)

#add_library(native_app_glue STATIC
#        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c)
#target_include_directories(native_app_glue PUBLIC
#        ${ANDROID_NDK}/sources/android/native_app_glue)


find_library(log-lib
        log)

set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11 -O3")

add_library(Convolution SHARED
        src/main/cpp/convolution.cpp)

target_link_libraries(Convolution
#        android
#        native_app_glue
        ${log-lib})
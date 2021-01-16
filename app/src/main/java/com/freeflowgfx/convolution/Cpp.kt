package com.freeflowgfx.convolution

class Cpp {
    @Throws(IllegalArgumentException::class)
    external fun convolution(signal: FloatArray, filter: FloatArray): FloatArray

    companion object {
        init {
            System.loadLibrary("Convolution")
        }
    }
}

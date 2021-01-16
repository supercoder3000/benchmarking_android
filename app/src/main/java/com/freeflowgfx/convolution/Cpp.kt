package com.freeflowgfx.convolution

class Cpp {
    @Throws(IllegalArgumentException::class)
    external fun convolution(a: Int, b: Int): Int

    companion object {
        init {
            System.loadLibrary("Convolution")
        }
    }
}
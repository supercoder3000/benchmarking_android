package com.freeflowgfx.convolution.implementation

class Cpp : FunctionsUnderTest {
    override val implementationType = "Cpp"

    @Throws(IllegalArgumentException::class)
    external override fun convolution(signal: FloatArray, filter: FloatArray): FloatArray

    companion object {
        init {
            System.loadLibrary("Convolution")
        }
    }
}

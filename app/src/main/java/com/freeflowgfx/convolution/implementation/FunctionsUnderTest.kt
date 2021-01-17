package com.freeflowgfx.convolution.implementation

interface FunctionsUnderTest {
    val implementationType: String
    fun convolution(signal: FloatArray, filter: FloatArray): FloatArray
}

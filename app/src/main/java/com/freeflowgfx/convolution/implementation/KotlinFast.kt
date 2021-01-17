package com.freeflowgfx.convolution.implementation

class KotlinFast : FunctionsUnderTest {
    override val implementationType = "KotlinFast"

    override fun convolution(signal: FloatArray, filter: FloatArray): FloatArray {

        val result = FloatArray(signal.size)

        for (i in signal.indices) {
            for (j in filter.indices) {
                val signalIndex: Int = i + j - filter.size / 2
                if (signalIndex >= 0 && signalIndex < signal.size) {
                    result[i] += signal[signalIndex] * filter[filter.size - j - 1]
                }
            }
        }

        return result
    }
}

package com.freeflowgfx.convolution

class Kotlin {

    fun convolution(signal: List<Float>, filter: List<Float>): List<Float> {
        val filterReversed = filter.reversed()

        return (List(filter.size / 2) { 0f } + signal + List(filter.size / 2 - 1) { 0f }).windowed(
            filter.size
        ) {
            it.zip(filterReversed).map {
                it.first * it.second
            }.sum()
        }
    }

    fun convolutionFast(signal: FloatArray, filter: FloatArray): FloatArray {

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

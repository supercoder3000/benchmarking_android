package com.freeflowgfx.convolution

class Kotlin {

    fun convolution(signal: List<Float>, filter: List<Float>): List<Float> {
        val filterReversed = filter.reversed()

        return (List(filter.size / 2) { 0f } + signal + List(filter.size / 2) { 0f }).windowed(
            filter.size
        ) {
            it.zip(filterReversed).map {
                it.first * it.second
            }.sum()
        }
    }

//    fun convolutionExt(signal: FloatArray, filter: FloatArray): F {
//        val filterReversed = filter.reversed()
//
//        return signal.windowed(filter.size) {
//            it.zip(filterReversed).map {
//                it.first * it.second
//            }.sum()
//        }
//    }
}

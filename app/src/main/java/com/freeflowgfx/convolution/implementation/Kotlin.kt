package com.freeflowgfx.convolution.implementation

class Kotlin : FunctionsUnderTest {
    override val implementationType = "Kotlin"

    override fun convolution(signal: FloatArray, filter: FloatArray): FloatArray {
        val filterReversed = filter.reversed()

        return (List(filter.size / 2) { 0f } + signal.toList() + List(filter.size / 2 - 1) { 0f }).windowed(
            filter.size
        ) {
            it.zip(filterReversed).map {
                it.first * it.second
            }.sum() //
        }.toFloatArray()
    }
}

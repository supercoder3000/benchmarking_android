package com.freeflowgfx.convolution

import com.freeflowgfx.convolution.implementation.FunctionsUnderTest
import org.hamcrest.MatcherAssert.assertThat

// import kotlin.test.assertTrue

abstract class CommonTest {

    abstract val implementation: FunctionsUnderTest

    private val filterShort = listOf(
        0.00735294f, 0.00942224f, 0.01542759f, 0.02478114f, 0.0365673f,
        0.04963235f, 0.06269741f, 0.07448357f, 0.08383712f, 0.08984246f,
        0.09191176f, 0.08984246f, 0.08383712f, 0.07448357f, 0.06269741f,
        0.04963235f, 0.0365673f, 0.02478114f, 0.01542759f, 0.00942224f,
        0.00735294f
    )

    private val filter = (filterShort + filterShort).toFloatArray()

    private fun assertConvolution(
        signal: FloatArray,
        expected: FloatArray
    ) {
        val res = implementation.convolution(
            signal = signal,
            filter = filter
        )

        assertThat("Length are not equal.", res.size == expected.size)

        expected.forEachIndexed { index, it ->
            assertThat("Found ${res.toList()}\n(expected $expected)", it == res[index])
        }
    }

    private fun getDiracSignal(): FloatArray {
        val signal = MutableList(1000) { 0f }
        signal[0] = 1f

        return signal.toFloatArray()
    }

    fun testDirac() {
        val signal = getDiracSignal()

        val expected = filter.takeLast(filter.size / 2 + 1) +
            List(signal.size - filter.size / 2 - 1) { 0f }

        assertConvolution(
            signal = signal,
            expected = expected.toFloatArray()
        )
    }

    fun testDoubleDirac() {
        val signal = MutableList(1000) { 0f }
        signal[0] = 1f
        signal[1] = 1f

        val expectedPeak0 = filter.takeLast(filter.size / 2 + 1) +
            List(signal.size - filter.size / 2 - 1) { 0f }
        val expectedPeak1 = filter.takeLast(filter.size / 2 + 2) +
            List(signal.size - filter.size / 2 - 2) { 0f }
        val expected = expectedPeak0.zip(expectedPeak1).map {
            it.first + it.second
        }

        assertConvolution(
            signal = signal.toFloatArray(),
            expected = expected.toFloatArray()
        )
    }
}

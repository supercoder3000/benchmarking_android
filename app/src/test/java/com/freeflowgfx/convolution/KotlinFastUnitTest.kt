package com.freeflowgfx.convolution

import org.junit.Test
import kotlin.test.assertTrue

class KotlinFastUnitTest {
    @Test
    fun dirac() {
        val kotlinFunctions = Kotlin()

        val signal = MutableList(1000) { 0f }
        signal[0] = 1f

        val res = kotlinFunctions.convolutionFast(
            signal = signal.toFloatArray(),
            filter = filter.toFloatArray()
        )

        val expected = filter.takeLast(filter.size / 2 + 1) +
            List(signal.size - filter.size / 2 - 1) { 0f }

        assertTrue("Length are not equal.") {
            res.size == expected.size
        }

        expected.forEachIndexed { index, it ->
            assertTrue("Found ${res.toList()}\n(expected $expected)") {
                it == res[index]
            }
        }
    }

    @Test
    fun doubleDirac() {
        val kotlinFunctions = Kotlin()

        val signal = MutableList(1000) { 0f }
        signal[0] = 1f
        signal[1] = 1f

        val res = kotlinFunctions.convolutionFast(
            signal = signal.toFloatArray(),
            filter = filter.toFloatArray()
        )

        val expectedPeak0 = filter.takeLast(filter.size / 2 + 1) +
            List(signal.size - filter.size / 2 - 1) { 0f }
        val expectedPeak1 = filter.takeLast(filter.size / 2 + 2) +
                List(signal.size - filter.size / 2 - 2) { 0f }
        val expected = expectedPeak0.zip(expectedPeak1).map {
            it.first + it.second
        }

        assertTrue("Length are not equal.") {
            res.size == expected.size
        }

        expected.forEachIndexed { index, it ->
            assertTrue("Found ${res.toList()}\n (expected $expected)") {
                it == res[index]
            }
        }
    }
}

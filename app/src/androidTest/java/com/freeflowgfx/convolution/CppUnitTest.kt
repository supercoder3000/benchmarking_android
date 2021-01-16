package com.freeflowgfx.convolution

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.RequiresDevice
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RequiresDevice
@RunWith(AndroidJUnit4::class)
class CppUnitTest {
    @Test
    fun dirac() {
        val cppFunctions = Cpp()

        val signal = MutableList(1000) { 0f }
        signal[0] = 1f

        val res = cppFunctions.convolution(
            signal = signal.toFloatArray(),
            filter = filter.toFloatArray()
        )

        val expected = filter.takeLast(filter.size / 2 + 1) +
            List(signal.size - filter.size / 2 - 1) { 0f }

        assertTrue("Length are not equal.", res.size == expected.size)

        expected.forEachIndexed { index, it ->
            assertTrue("Found ${res.toList()}\n(expected $expected)", (it - res[index] < 1e-9))
        }
    }

    @Test
    fun doubleDirac() {
        val cppFunctions = Cpp()

        val signal = MutableList(1000) { 0f }
        signal[0] = 1f
        signal[1] = 1f

        val res = cppFunctions.convolution(
            signal = signal.toFloatArray(),
            filter = filter.toFloatArray()
        )

        val expectedTmp = filter.takeLast(filter.size / 2 + 1) +
            List(signal.size - filter.size / 2 - 1) { 0f }
        val expected = expectedTmp.zip(listOf(expectedTmp[1]) + expectedTmp.dropLast(1)).map {
            it.first + it.second
        }

        assertTrue(
            "Length are not equal.",
            res.size == expected.size
        )

        expected.forEachIndexed { index, it ->
            assertTrue("Found ${res.toList()}\n (expected $expected)", (it - res[index]) < 1e-9)
        }
    }
}

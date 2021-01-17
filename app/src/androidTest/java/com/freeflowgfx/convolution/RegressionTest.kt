package com.freeflowgfx.convolution

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.RequiresDevice
import com.freeflowgfx.convolution.implementation.Cpp
import com.freeflowgfx.convolution.implementation.Kotlin
import com.freeflowgfx.convolution.implementation.KotlinFast
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RequiresDevice
@RunWith(AndroidJUnit4::class)
class RegressionTest {

    @Test
    fun random() {
        val cppFunctions = Cpp()
        val kotlinFunctions = Kotlin()
        val kotlinFastFunctions = KotlinFast()

        val signalLength = 1000
        val numRepetitions = 10

        val signal = List(signalLength) { Random.nextFloat() }.toFloatArray()

        for (ii in 1..numRepetitions) {
            val cppRes = cppFunctions.convolution(
                signal = signal,
                filter = filter
            )

            val kotlinRes = kotlinFunctions.convolution(
                signal = signal,
                filter = filter
            )

            val kotlinFastRes = kotlinFastFunctions.convolution(
                signal = signal,
                filter = filter
            )

            Assert.assertTrue(
                "Cpp and kotlin do not have the same length: ${cppRes.size} vs ${kotlinRes.size}",
                cppRes.size == kotlinRes.size
            )
            Assert.assertTrue(
                "kotlinFast and kotlin do not have the same length: ${kotlinFastRes.size} vs ${kotlinRes.size}",
                kotlinFastRes.size == kotlinRes.size
            )

            cppRes.forEachIndexed { index, it ->
                Assert.assertTrue(
                    "Cpp vs Kotlin difference at ${index}th element: $it vs ${kotlinRes[index]}",
                    it == kotlinRes[index]
                )

                Assert.assertTrue(
                    "KotlinFast vs Kotlin difference at ${index}th element: ${kotlinFastRes[index]} vs ${kotlinRes[index]}",
                    kotlinFastRes[index] == kotlinRes[index]
                )
            }
        }
    }
}

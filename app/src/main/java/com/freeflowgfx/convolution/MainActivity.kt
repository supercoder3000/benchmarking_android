package com.freeflowgfx.convolution

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val oneMilliSecondInNanoSeconds = 1000000

    private fun benchmark(numRepetitions: Int, fn: () -> Unit): Long {
        val timeStart = System.nanoTime()
        for (ii in 0 until numRepetitions) {
            fn()
        }
        return System.nanoTime() - timeStart
    }

    fun runConvolution() {
        val signalLength = 1000
        val numRepetitions = 100

        val signal = List(signalLength) { Random.nextFloat() }

        val cppFunctions = Cpp()

        val timeKotlin = benchmark(numRepetitions) { kotlinConvolution(signal) }
        val timeCpp = benchmark(numRepetitions) { cppFunctions.convolution(0, 1) }

        val kotlinTextView = findViewById<TextView>(R.id.timeKotlin)
        kotlinTextView.text =
            "Kotlin code took ${timeKotlin / oneMilliSecondInNanoSeconds}ms for $numRepetitions iterations"

        val cppTextView = findViewById<TextView>(R.id.timeCpp)
        cppTextView.text =
            "C++ code took ${timeCpp / oneMilliSecondInNanoSeconds}ms for $numRepetitions iterations"
    }

    private fun kotlinConvolution(signal: List<Float>): List<Float> {
        val filter = listOf(
            0.08f, 0.102514f, 0.16785218f, 0.26961878f, 0.39785218f,
            0.54f, 0.68214782f, 0.81038122f, 0.91214782f, 0.977486f,
            1.0f, 0.977486f, 0.91214782f, 0.81038122f, 0.68214782f,
            0.54f, 0.39785218f, 0.26961878f, 0.16785218f, 0.102514f,
            0.08f
        )

        val filterReversed = filter.reversed()

        return signal.windowed(filter.size) {
            it.zip(filterReversed).map {
                it.first * it.second
            }.sum()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runConvolution()
    }
}
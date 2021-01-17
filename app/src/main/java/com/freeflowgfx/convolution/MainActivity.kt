package com.freeflowgfx.convolution

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.freeflowgfx.convolution.benchmark.benchmark
import com.freeflowgfx.convolution.implementation.Cpp
import com.freeflowgfx.convolution.implementation.FunctionsUnderTest
import com.freeflowgfx.convolution.implementation.Kotlin
import com.freeflowgfx.convolution.implementation.KotlinFast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val oneMilliSecondInNanoSeconds = 1000000

    private fun runConvolution() {
        val signalLength = 10000
        val numRepetitions = 50

        val signal = List(signalLength) { Random.nextFloat() }.toFloatArray()

        val implementationsUnderTest: List<FunctionsUnderTest> = listOf(
            Cpp(),
            Kotlin(),
            KotlinFast()
        )

        val testResults = implementationsUnderTest.map {
            val duration = benchmark(numRepetitions) {
                it.convolution(
                    signal = signal,
                    filter = filter
                )
            }
            "${it.implementationType} code took ${duration / oneMilliSecondInNanoSeconds}ms"
        }

        val infoTextView = findViewById<TextView>(R.id.infoText)
        infoTextView.text = "Convolution Benchmark: $numRepetitions iterations"

        val benchmarkResultsTextView = findViewById<TextView>(R.id.benchmarkResults)
        benchmarkResultsTextView.text = testResults.joinToString("\n")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runConvolution()
    }
}

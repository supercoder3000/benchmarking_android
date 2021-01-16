package com.freeflowgfx.convolution

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val oneMilliSecondInNanoSeconds = 1000000

    fun runConvolution() {
        val signalLength = 1000
        val numRepetitions = 100

        val signal = List(signalLength) { Random.nextFloat() }

        val timeStart = System.nanoTime()
        for (ii in 0 until numRepetitions) {
            computeConvolution(signal)
        }
        val timeEnd = System.nanoTime()

        val kotlinEdit = findViewById<TextView>(R.id.timeKotlin)
        kotlinEdit.text =
            "Kotlin code took ${(timeEnd - timeStart) / oneMilliSecondInNanoSeconds}ms for $numRepetitions iterations"
    }

    private fun computeConvolution(signal: List<Float>): List<Float> {
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
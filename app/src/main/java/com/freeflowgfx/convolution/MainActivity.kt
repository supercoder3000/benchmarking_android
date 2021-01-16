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
        val numRepetitions = 1000

        val signal = List(signalLength) { Random.nextFloat() }

        val cppFunctions = Cpp()
        val kotlinFunctions = Kotlin()

        val timeKotlin = benchmark(numRepetitions) { kotlinFunctions.convolution(signal, filter) }
        val timeCpp = benchmark(numRepetitions) { cppFunctions.convolution(signal.toFloatArray(), filter.toFloatArray()) }

        val kotlinTextView = findViewById<TextView>(R.id.timeKotlin)
        kotlinTextView.text =
            "Kotlin code took ${timeKotlin / oneMilliSecondInNanoSeconds}ms for $numRepetitions iterations"

        val cppTextView = findViewById<TextView>(R.id.timeCpp)
        cppTextView.text =
            "C++ code took ${timeCpp / oneMilliSecondInNanoSeconds}ms for $numRepetitions iterations"
    }

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runConvolution()
    }
}

package com.freeflowgfx.convolution.benchmark

fun benchmark(numRepetitions: Int, fn: () -> Unit): Long {
    val timeStart = System.nanoTime()
    for (ii in 0 until numRepetitions) {
        fn()
    }
    return System.nanoTime() - timeStart
}

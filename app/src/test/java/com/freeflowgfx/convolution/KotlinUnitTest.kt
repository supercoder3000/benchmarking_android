package com.freeflowgfx.convolution

import com.freeflowgfx.convolution.implementation.Kotlin
import org.junit.Test

class KotlinUnitTest : CommonTest() {
    override val implementation = Kotlin()

    @Test
    fun dirac() {
        super.testDirac()
    }

    @Test
    fun doubleDirac() {
        super.testDoubleDirac()
    }
}

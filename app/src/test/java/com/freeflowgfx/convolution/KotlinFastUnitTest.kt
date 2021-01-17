package com.freeflowgfx.convolution

import com.freeflowgfx.convolution.implementation.KotlinFast
import org.junit.Test

class KotlinFastUnitTest : CommonTest() {
    override val implementation = KotlinFast()

    @Test
    fun dirac() {
        super.testDirac()
    }

    @Test
    fun doubleDirac() {
        super.testDoubleDirac()
    }
}

package com.freeflowgfx.convolution

import androidx.test.filters.RequiresDevice
import com.freeflowgfx.convolution.implementation.Cpp
import org.junit.Test

@RequiresDevice
class CppUnitTest : CommonTest() {
    override val implementation = Cpp()

    @Test
    fun dirac() {
        super.testDirac()
    }

    @Test
    fun doubleDirac() {
        super.testDoubleDirac()
    }
}


package com.freeflowgfx.sequence

import com.freeflowgfx.convolution.sequence.distance
import org.junit.Test

class LevenshteinUnitTest() {
    @Test
    fun equal() {
        val dist = distance("aaa", "aaa")
        val expected = 0

        assert(dist == expected)
    }

    @Test
    fun replace() {
        val dist = distance("aba", "aaa")
        val expected = 1

        assert(dist == expected)
    }

    @Test
    fun insert() {
        val dist = distance("bbbaabb", "bbbaaabb")
        val expected = 1

        assert(dist == expected)
    }

    @Test
    fun delete() {
        val dist = distance("dtor", "dtier")
        val expected = 2

        assert(dist == expected)
    }
}

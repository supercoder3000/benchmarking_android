package com.freeflowgfx.convolution

val filterShort = listOf(
    0.00735294f, 0.00942224f, 0.01542759f, 0.02478114f, 0.0365673f,
    0.04963235f, 0.06269741f, 0.07448357f, 0.08383712f, 0.08984246f,
    0.09191176f, 0.08984246f, 0.08383712f, 0.07448357f, 0.06269741f,
    0.04963235f, 0.0365673f, 0.02478114f, 0.01542759f, 0.00942224f,
    0.00735294f
)

val filter =
    (
        filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort +
            filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort + filterShort
        ).toFloatArray()

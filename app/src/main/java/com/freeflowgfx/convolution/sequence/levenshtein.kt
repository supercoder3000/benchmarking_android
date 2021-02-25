package com.freeflowgfx.convolution.sequence

fun distance(lhs: CharSequence, rhs: CharSequence): Int {
    var cost = IntArray(lhs.length + 1) { it }
    var newCost = IntArray(lhs.length + 1)

    for (j in 1..rhs.length) {
        newCost[0] = j

        for (i in 1..lhs.length) {
            val match = if (lhs[i - 1] == rhs[j - 1]) 0 else 1

            val replaceCost = cost[i - 1] + match
            val insertCost = cost[i] + 1
            val deleteCost = newCost[i - 1] + 1

            newCost[i] = minOf(insertCost, deleteCost, replaceCost)
        }

        val swap = cost
        cost = newCost
        newCost = swap
    }

    return cost.last()
}

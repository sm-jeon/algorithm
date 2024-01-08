package two차원_동전_뒤집기

import kotlin.math.min
import kotlin.math.pow

//https://school.programmers.co.kr/learn/courses/30/lessons/131703
fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
    val diff = buildDiff(beginning, target)

    var minValue = Int.MAX_VALUE
    for(bit in 0 until 2.0.pow(diff[0].size).toInt()) {
        val bitMask = bit.toString(2).padStart(diff[0].size, '0').toList().map { it=='1' }
        var result = true
        var needColumnReverse = 0

        for(i in diff.indices) {
            for(j in 1 until diff[0].size) {
                if(bitMask[0] xor diff[i][0] != bitMask[j] xor diff[i][j]) {
                    result = false
                }
            }
        }
        for(i in diff.indices) {
            if(!(bitMask[0] xor diff[i][0])) needColumnReverse++
        }
        if(result) {
            minValue = min(minValue, bitMask.count { it } + needColumnReverse)
        }
    }
    return if(minValue==Int.MAX_VALUE) -1 else minValue
}

fun buildDiff(beginning: Array<IntArray>, target: Array<IntArray>): MutableList<MutableList<Boolean>> {
    val diff = arrayListOf<MutableList<Boolean>>()
    for(i in beginning.indices) {
        diff.add(arrayListOf())
        for(j in beginning[0].indices) {
            diff[i].add(beginning[i][j] == target[i][j])
        }
    }
    return diff
}

class InputCase {
    companion object {
        val beginning = arrayOf(
            intArrayOf(0, 1, 0, 0, 0),
            intArrayOf(1, 0, 1, 0, 1),
            intArrayOf(0, 1, 1, 1, 0),
            intArrayOf(1, 0, 1, 1, 0),
            intArrayOf(0, 1, 0, 1, 0),
        )

        val target = arrayOf(
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 0, 1),
            intArrayOf(0, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 0, 0, 0, 1),
        )
    }
}
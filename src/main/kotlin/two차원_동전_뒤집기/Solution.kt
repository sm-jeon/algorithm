package two차원_동전_뒤집기

import kotlin.math.min
import kotlin.math.pow

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/131703
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-2%EC%B0%A8%EC%9B%90-%EB%8F%99%EC%A0%84-%EB%92%A4%EC%A7%91%EA%B8%B0
 */
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
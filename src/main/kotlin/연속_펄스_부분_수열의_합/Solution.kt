package 연속_펄스_부분_수열의_합

import kotlin.math.max
import kotlin.collections.max

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/161988
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%97%B0%EC%86%8D-%ED%8E%84%EC%8A%A4-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4%EC%9D%98-%ED%95%A9
 */
fun solution(sequence: IntArray): Long {
    val mapper1 = intArrayOf(1,-1)
    val case1 = sequence.mapIndexed { index, i ->
        (i * mapper1[index%2]).toLong()
    }
    val mapper2 = intArrayOf(-1,1)
    val case2 = sequence.mapIndexed { index, i ->
        (i * mapper2[index%2]).toLong()
    }
    val dp1 = arrayListOf(max(0, case1[0]))
    for(i in 1 until case1.size) {
        dp1.add(max(max(dp1[i-1] + case1[i], case1[i]), 0))
    }
    val dp2 = arrayListOf(max(0, case2[0]))
    for(i in 1 until case2.size) {
        dp2.add(max(max(dp2[i-1] + case2[i], case2[i]), 0))
    }
    return max(dp1.maxOf { it }, dp2.maxOf { it })
}

class InputCase {
    companion object {
        val sequence = intArrayOf(2,3,-6,1,3,-1,2,4)
    }
}
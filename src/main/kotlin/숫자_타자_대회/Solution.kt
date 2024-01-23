package 숫자_타자_대회

import kotlin.math.min

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/136797
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%88%AB%EC%9E%90-%ED%83%80%EC%9E%90-%EB%8C%80%ED%9A%8C
 */

val cost = arrayOf(
    intArrayOf(1,7,6,7,5,4,5,3,2,3,),
    intArrayOf(7,1,2,4,2,3,5,4,5,6,),
    intArrayOf(6,2,1,2,3,2,3,5,4,5,),
    intArrayOf(7,4,2,1,5,3,2,6,5,4,),
    intArrayOf(5,2,3,5,1,2,4,2,3,5,),
    intArrayOf(4,3,2,3,2,1,2,3,2,3,),
    intArrayOf(5,5,3,2,4,2,1,5,3,2,),
    intArrayOf(3,4,5,6,2,3,5,1,2,4,),
    intArrayOf(2,5,4,5,3,2,3,2,1,2,),
    intArrayOf(3,6,5,4,5,3,2,4,2,1,),
)

fun solution(numbers: String): Int {
    val memo = Array(numbers.length+1) { idx ->
        Array(10) {
            IntArray(10) { Int.MAX_VALUE }
        }
    }

    memo[0][4][6] = 0

    for(i in 1 .. numbers.length) {
        var target = numbers[i-1].digitToInt()
        for(l in 0..9) {
            for(r in 0..9) {
                if(memo[i-1][l][r] == Int.MAX_VALUE) continue
                if(target != r) memo[i][target][r] = min(memo[i][target][r], memo[i-1][l][r] + cost[l][target])
                if(target != l) memo[i][l][target] = min(memo[i][l][target], memo[i-1][l][r] + cost[r][target])
            }
        }
    }
    return memo.last().minOf { it.minOf { it } }
}

class InputCase {
    companion object {
        val numbers = "5123"
    }
}
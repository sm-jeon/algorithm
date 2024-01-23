package 등대

import kotlin.math.min

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/133500
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%93%B1%EB%8C%80
 */

fun solution(n: Int, lighthouse: Array<IntArray>): Int {
    val visit = BooleanArray(n+1)
    val dp = Array(n+1){ IntArray(2) }
    val edge = mutableMapOf<Int, MutableList<Int>>()
    lighthouse.map { it[0] to it[1] }.forEach {
        edge[it.first] = edge.getOrDefault(it.first, arrayListOf()).apply { add(it.second) }
        edge[it.second] = edge.getOrDefault(it.second, arrayListOf()).apply { add(it.first) }
    }
    dfs(dp, visit, edge, 1)
    return min(dp[1][0], dp[1][1])
}

fun dfs(dp: Array<IntArray>, visit: BooleanArray, edge: MutableMap<Int, MutableList<Int>>, node: Int) {
    if(visit[node]) return
    visit[node] = true
    if(edge[node]!!.size==1) {
        dp[node][0] = 0
        dp[node][1] = 1
    }
    dp[node][1] = 1
    edge[node]!!.forEach {
        if(visit[it]) return@forEach
        dfs(dp, visit, edge, it)
        dp[node][0] += dp[it][1]
        dp[node][1] += min(dp[it][0], dp[it][1])
    }
}

class InputCase {
    companion object {
        val n = 8
        val lighthouse = arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(1, 5),
            intArrayOf(5, 6),
            intArrayOf(5, 7),
            intArrayOf(5, 8),
        )
    }
}
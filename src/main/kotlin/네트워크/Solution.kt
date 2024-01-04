package 네트워크

// https://school.programmers.co.kr/learn/courses/30/lessons/43162
fun solution(n: Int, computers: Array<IntArray>): Int {
    val visited = BooleanArray(n) { false }
    var result = 0
    for(i in 0 until n) {
        if (!visited[i]) {
            dfs(computers, visited, i)
            result++
        }
    }
    return result
}

fun dfs(computers: Array<IntArray>, visited: BooleanArray, computerIdx: Int) {
    if(visited[computerIdx]) return
    visited[computerIdx] = true
    computers[computerIdx].forEachIndexed { idx, it ->
        if(it==1) dfs(computers, visited, idx)
    }
}


class InputCase {
    companion object {
        val n = 3
        val computers = arrayOf(
            intArrayOf(1,1,0),
            intArrayOf(1,1,0),
            intArrayOf(0,0,1),
        )
    }
}
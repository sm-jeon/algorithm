package 네트워크

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/43162
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC
 */
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
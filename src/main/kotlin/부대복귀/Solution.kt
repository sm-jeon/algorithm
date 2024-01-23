package 부대복귀



//https://school.programmers.co.kr/learn/courses/30/lessons/132266
fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
    val edge = Array(n+1) { arrayListOf<Int>() }
    val distance = IntArray(n+1) { -1 }
    val visit = BooleanArray(n+1)
    distance[destination] = 0

    roads.forEach {
        edge[it[0]].add(it[1])
        edge[it[1]].add(it[0])
    }

    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(destination to 0)

    for((node, cost) in queue) {
        if(visit[node]) continue
        visit[node] = true

        distance[node] = cost
        edge[node].forEach {
            queue.add(it to cost + 1)
        }
    }

    return IntArray(sources.size) { distance[sources[it]] }
}

class InputCase {
    companion object { // 5	[[1, 2], [1, 4], [2, 4], [2, 5], [4, 5]]	[1, 3, 5]	5	[2, -1, 0]
        val n = 5
        val roads = arrayOf(
            intArrayOf(1,2),
            intArrayOf(1,4),
            intArrayOf(2,4),
            intArrayOf(2,5),
            intArrayOf(4,5),
        )
        val sources = intArrayOf(1,3,5)
        val destination = 5
    }
}
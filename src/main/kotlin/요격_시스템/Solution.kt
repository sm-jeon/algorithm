package 요격_시스템

import java.util.Queue

//https://school.programmers.co.kr/learn/courses/30/lessons/181188
fun solution(targets: Array<IntArray>): Int {
    val queue = ArrayDeque<IntArray>(0)
    targets.sortedBy { it[1] }.forEach { queue.add(it) }
    var result = 0
    while(queue.isNotEmpty()) {
        val targetIndex = queue.removeFirst()
        while(queue.isNotEmpty() && targetIndex[1] > queue.first()[0]) {
            queue.removeFirst()
        }
        result++
    }
    return result
}

class InputCase {
    companion object {
        val targets = arrayOf(
            intArrayOf(4,5),
            intArrayOf(4,8),
            intArrayOf(10,14),
            intArrayOf(11,13),
            intArrayOf(5,12),
            intArrayOf(3,7),
            intArrayOf(1,4),
        )
    }
}
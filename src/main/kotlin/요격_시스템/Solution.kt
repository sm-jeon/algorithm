package 요격_시스템

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/181188
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9A%94%EA%B2%A9-%EC%8B%9C%EC%8A%A4%ED%85%9C
 */
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
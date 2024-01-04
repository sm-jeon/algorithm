package 타겟_넘버

// https://school.programmers.co.kr/learn/courses/30/lessons/43165
fun solution(numbers: IntArray, target: Int): Int {
    return dfs(numbers, 0, 0, target)
}

fun dfs(numbers: IntArray, idx: Int, res: Int, target: Int): Int {
    if(idx == numbers.size) return if(res == target) 1 else 0

    return dfs(numbers, idx+1, res + numbers[idx], target) +
            dfs(numbers, idx+1, res - numbers[idx], target)
}

class InputCase {
    companion object {
        val numbers = intArrayOf(1,1,1,1,1)
        val target = 3
    }
}
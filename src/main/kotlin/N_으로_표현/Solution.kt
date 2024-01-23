package N_으로_표현

import kotlin.math.pow

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/42895?language=kotlin
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-N%EC%9C%BC%EB%A1%9C-%ED%91%9C%ED%98%84
 */

fun solution(N: Int, num: Int): Int {
    val arr = Array<MutableSet<Int>>(9) { mutableSetOf() }
    for(i in 1..8) {
        arr[i].add(makeNum(N, i))
        for(j in 1 until i) {
            arr[j].forEach { first ->
                arr[i-j].forEach {  second ->
                    arr[i].add(first + second)
                    arr[i].add(first - second)
                    arr[i].add(first * second)
                    if(second != 0) arr[i].add(first / second)
                }
            }
        }
        if(arr[i].contains(num)) return i
    }
    return -1
}

fun makeNum(N: Int, count: Int): Int {
    return (0 until count).fold(0) { a, c ->
        a + 10.0.pow(c).toInt() * N
    }
}

class InputCase {
    companion object {
        val N = 5
        val num = 12
    }
}
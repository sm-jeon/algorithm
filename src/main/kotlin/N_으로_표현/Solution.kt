package N_으로_표현

import kotlin.math.pow

//https://school.programmers.co.kr/learn/courses/30/lessons/42895?language=kotlin
//
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
package 아방가르드_타일링

import kotlin.math.min

//https://school.programmers.co.kr/learn/courses/30/lessons/181186
// 1 : 세로 하나: 1 => drawType[1]
// 2 : 기억자 두개 2 => drawType[2] + drawType[1] / drawType[1] + drawType[2]
// 3 : 기억자 두개 가로선 하나 4 + 가로선 3개 1 => drawType[3] / drawType[2] + drawType[1] / drawType[1] + drawType[2] / drawType[1] + drawType[1] + drawType[1]
// 4 : drawType[3] + drawType[1] / drawType[1] + drawType[3] / drawType[2] + drawType[2] / drawType[1] +drawType[1] +drawType[1] +drawType[1]
/*
1 // 1
2, 11 //  2 + 1
3, 21, 12, 111 // 5 + 2 + 2 + 1
31, 13, 22, 211, 121, 112, 1111 // 5 + 5 + 4 + 2 + 2 + 2 + 1 // 21
32, 23, 311, 113, 131, 122, 212, 221, 2111, 1211, 1121, 1112, 11111 // 10 + 10 + 5 + 5 + 5 + 4 + 4 + 4 + 2 + 2 + 2 + 2 + 1 // 56
33, 321, 312, 123, 213, 3111, 1113, 1131, 1311, 122, 212, 221, 2111, 1211, 1121, 1112, 11111 // 10 + 10 + 5 + 5 + 5 + 4 + 4 + 4 + 2 + 2 + 2 + 2 + 1 // 56
*/
fun solution(n: Int): Int {
    val drawType = intArrayOf(1,2,5)
    val specialDrawType = intArrayOf(2,2,4)
    val specialMemo = arrayListOf(0,0,0)
    val result = IntArray(n)
    result[0] = 1
    for(i in 1 until n) {
        for(j in 0 until drawType.size) {
            if(i < j) break
            if(i == j) result[i] += drawType[i]
            else result[i] += result[i-j-1] * drawType[j]
        }
        for(j in 3 .. i) {
            if(j==i) {
                print("1 * ${specialDrawType[j%3]} ")
                result[i] += specialDrawType[j%3]
            } else {
                print("${result[i-j-1]} * ${specialDrawType[j%3]} + ")
                result[i] += result[i-j-1] * specialDrawType[j%3]
            }
        }
        result[i] %= 1000000007
        println()
    }
    println(result.toList())
    return result.last()
}

class InputCase {
    companion object {
        val n = 15
    }
}
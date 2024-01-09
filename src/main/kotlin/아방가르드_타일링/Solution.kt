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
/*
dp[i] = dp[i-1] + dp[i-2]*2 + dp[i-3]*5 + dp[i-4]*2 + dp[i-5]*2 + dp[i-6]*4 + dp[i-7]*2 + dp[i-8]*2 + dp[i-9]*4 ...
dp[i-3] = dp[i-4] + dp[i-5]*2 + dp[i-6]*5 + dp[i-7]*2 + dp[i-8]*2 + dp[i-9]*4 ...
dp[i] - dp[i-3] = dp[i-1] + dp[i-2]*2 + dp[i-3]*5 + dp[i-4] - dp[i-6]
dp[i] = dp[i-1] + dp[i-2]*2 + dp[i-3]*6 + dp[i-4] - dp[i-6]
 */
fun solution(n: Int): Int {
    val dp = arrayListOf<Long>(1,1,3,10,23,62)
    while(dp.size < n+1) {
        dp.add((dp[dp.size-1] + dp[dp.size-2]*2 + dp[dp.size-3]*6 + dp[dp.size-4] - dp[dp.size-6] + 1000000007) % 1000000007)
    }
    return dp[n].toInt()
}

class InputCase {
    companion object {
        val n = 9
    }
}
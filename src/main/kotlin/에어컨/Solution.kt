package 에어컨

//https://school.programmers.co.kr/learn/courses/30/lessons/214289
/*
시간이 갈때마다의 경우의 수: 3가지.
    1. 에어컨을 가동해 희망온도에 가까워지는 경우 (a 전력 소비)
        1.1. 희망온도를 조절해 온도를 높이는 경우
        1.2. 희망온도를 조절해 온도를 낮추는 경우
    2. 에어컨을 가동해 희망온도를 유지하는 경우 (b 전력 소비)
    3. 에어컨을 가동하지 않는 경우(전력 0 소비)
        3.1. 실내온도가 내려가는 경우(실외온도가 더 낮음)
        3.2. 실내온도가 올라가는 경우(실외온도가 더 높음)
        3.3. 실내온도가 유지되는 경우(실외온도와 같음)

온도는 -10~40 범위 안.
*/
fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
    val temperatureRetouch = 11
    val dp = Array(onboard.size) { idx -> IntArray(53) { 100*10000 } }
    dp[0][temperature+temperatureRetouch] = 0
    for (i in 1 until dp.size) {
        for(t in 1..51) {
            val currentTemperature = t - temperatureRetouch
            if(onboard[i]==1) {
                if(currentTemperature !in t1..t2) continue
            }
            val off = if(currentTemperature > temperature) -1 else if(currentTemperature < temperature) 1 else 0
            dp[i][t] = intArrayOf(
                dp[i-1][t - 1] + a,
                dp[i-1][t + 1] + a,
                dp[i-1][t] + b,
                if(off==0) intArrayOf(dp[i-1][t-1], dp[i-1][t], dp[i-1][t+1],).min() else dp[i-1][t-off],
            ).min()
        }
    }
    return dp.last().min()
}

class InputCase {
    companion object {
//        val temperature = 28
//        val t1 = 18
//        val t2 = 26
//        val a = 10
//        val b = 8
//        val onboard = intArrayOf(0,0,1,1,1,1,1)

        val temperature = -1
        val t1 = 3
        val t2 = 4
        val a = 2
        val b = 3
        val onboard = intArrayOf(0, 0, 0, 0, 1, 0, 0, 0, 0,0)
    }
}
package 카운트_다운

/*
문제 URL
https://school.programmers.co.kr/learn/courses/30/lessons/131129
티스토리 URL
https://sm-jeon-develop.tistory.com/entry/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B9%B4%EC%9A%B4%ED%8A%B8-%EB%8B%A4%EC%9A%B4
*/
fun solution(target: Int): IntArray {
    val dp = Array(target+1) { Int.MAX_VALUE to Int.MAX_VALUE }
    dp[0] = 0 to 0
    for(i in 1 until dp.size) {
        for(j in 1..20) { // 싱글
            if(i < j) break
            val temp = dp[i-j].first + 1 to dp[i-j].second + 1
            dp[i] = max(temp, dp[i])
        }
        for (j in 22..40 step 2) { // 더블
            if(i < j) break
            val temp = dp[i-j].first + 1 to dp[i-j].second
            dp[i] = max(temp, dp[i])
        }
        for(j in 21..60 step 3) { // 트리플
            if(i < j) break
            val temp = dp[i-j].first + 1 to dp[i-j].second
            dp[i] = max(temp, dp[i])
        } // 불
        if(i < 50) continue
        val temp = dp[i-50].first + 1 to dp[i-50].second + 1
        dp[i] = max(temp, dp[i])
    }
    return intArrayOf(dp.last().first, dp.last().second)
}

fun max(a: Pair<Int, Int>, b: Pair<Int, Int>) = if(a.first < b.first) a else if(a.first == b.first && a.second > b.second) a else b

class InputCase {
    companion object {
        val target = 21 // 58
    }
}
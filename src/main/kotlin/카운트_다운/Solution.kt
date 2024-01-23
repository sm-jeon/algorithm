package 카운트_다운

//https://school.programmers.co.kr/learn/courses/30/lessons/131129
fun solution(target: Int): IntArray {
    val dp = Array<Pair<Int, Int>>(target+1) { Int.MAX_VALUE to Int.MAX_VALUE }
    dp[0] = 0 to 0
    for(i in 1 until dp.size) {
        for(j in 1..20) { // 싱글
            if(i < j) break
            val temp = dp[i-j].first + 1 to dp[i-j].second + 1
            dp[i] = if(temp.first < dp[i].first) temp
            else if(temp.first == dp[i].first && temp.second > dp[i].second) temp
            else dp[i]
        }
        for (j in 22..40 step 2) { // 더블
            if(i < j) break
            val temp = dp[i-j].first + 1 to dp[i-j].second
            dp[i] = if(temp.first < dp[i].first) temp
            else if(temp.first == dp[i].first && temp.second > dp[i].second) temp
            else dp[i]
        }
        for(j in 21..60 step 3) { // 트리플
            if(i < j) break
            val temp = dp[i-j].first + 1 to dp[i-j].second
            dp[i] = if(temp.first < dp[i].first) temp
            else if(temp.first == dp[i].first && temp.second > dp[i].second) temp
            else dp[i]
        } // 불
        if(i < 50) continue
        val temp = dp[i-50].first + 1 to dp[i-50].second + 1
        dp[i] = if(temp.first < dp[i].first) temp
        else if(temp.first == dp[i].first && temp.second > dp[i].second) temp
        else dp[i]
    }
    return intArrayOf(dp.last().first, dp.last().second)
}

class InputCase {
    companion object {
        val target = 21 // 58
    }
}
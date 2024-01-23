package 정수_삼각형;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/43105?language=java
class Solution {
    public int solution(int[][] triangle) {
        int[][] memo = triangle.clone();
        for(int i=1; i<triangle.length; i++) {
            memo[i][0] = memo[i-1][0] + triangle[i][0];
            for(int j=1; j<triangle[i].length-1; j++) {
                memo[i][j] = Math.max(memo[i-1][j], memo[i-1][j+1]) + triangle[i][j];
            }
            int lastIdx = triangle[i].length-1;
            memo[i][lastIdx] = memo[i-1][lastIdx-1] + triangle[i][lastIdx];
        }
        return Arrays.stream(memo[memo.length-1]).max().getAsInt();
    }
}
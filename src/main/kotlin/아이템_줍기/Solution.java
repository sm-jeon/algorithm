package 아이템_줍기;

import java.util.Arrays;

//https://school.programmers.co.kr/learn/courses/30/lessons/87694?language=java
public class Solution {

    public static int[][] rectangle
            = new int[][]{{1, 1, 8, 4},{2,2,4,9},{3, 6, 9, 8},{6,3,7,7}};
            //= new int[][]{{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
    public static int cX = 9;
    public static int cY = 7;
    public static int iX = 6;
    public static int iY = 1;
    static int size = 25;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[size][size];
        int[][] visit = new int[size][size];
        int cX = -1;
        int cY = -1;
        int iX = -1;
        int iY = -1;
        for(int i=0; i<size; i++) {
            Arrays.fill(map[i], -1);
        }
        for(int i=0; i<rectangle.length; i++) {
            int rStartX = rectangle[i][0];
            int rStartY = rectangle[i][1];
            int rEndX = rectangle[i][2];
            int rEndY = rectangle[i][3];
            for(int k = rStartY; k<=rEndY; k++) {
                for(int j= rStartX; j<=rEndX; j++) {
                    map[k*2][j*2] = 0;
                    map[k*2+1][j*2] = 0;
                    map[k*2][j*2+1] = 0;
                    map[k*2+1][j*2+1] = 0;
                }
            }
        }
        int totalDistance = 0;
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(map[i][j] == 0) {
                    if(i==0 || i==size-1 || j==0 || j==size-1) {
                        map[i][j] = 1;
                    } else if(map[i][j-1] == -1) {
                        map[i][j] = 1;
                    } else if(map[i][j+1] == -1) {
                        map[i][j] = 1;
                    } else if(map[i-1][j] == -1) {
                        map[i][j] = 1;
                    } else if(map[i+1][j] == -1) {
                        map[i][j] = 1;
                    } else if(map[i-1][j-1] == -1) {
                        map[i][j] = 1;
                    } else if(map[i-1][j+1] == -1) {
                        map[i][j] = 1;
                    } else if(map[i+1][j-1] == -1) {
                        map[i][j] = 1;
                    } else if(map[i+1][j+1] == -1) {
                        map[i][j] = 1;
                    } else {
                        continue;
                    }
                    if(i/2==characterY && j/2==characterX && cX==-1) {
                        cX = j;
                        cY = i;
                    } else if(i/2==itemY && j/2==itemX && iX==-1) {
                        iX = j;
                        iY = i;
                    }
                    totalDistance++;
                }
            }
        }
        int result = dfs(map, visit, cX, cY, iX, iY);
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(i==cY && j==cX) System.out.print("cc");
                else if(i==iY && j == iX) System.out.print("ii");
                else if(visit[i][j]==1) System.out.print("00");
                else System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println(result + " : " + totalDistance);
        return Math.min(result/2, totalDistance/2 - result/2);
    }

    public int dfs(int[][] map, int[][] visit, int x, int y, int targetX, int targetY) {
        if(visit[y][x]==1) return -1;
        if(x < 0 || x > size-1 || y < 0 || y > size-1) return -1;
        visit[y][x] = 1;
        if(x == targetX && y == targetY) return 0;
        int res = 9999999;
        if(map[y][x-1] == 1) {
            int result = dfs(map, visit, x-1, y, targetX, targetY);
            if(result != -1) res = Math.min(res, result) + 1;
        }
        if(map[y][x+1] == 1) {
            int result = dfs(map, visit, x+1, y, targetX, targetY);
            if(result != -1) res = Math.min(res, result) + 1;
        }
        if(map[y-1][x] == 1) {
            int result = dfs(map, visit, x, y-1, targetX, targetY);
            if(result != -1) res = Math.min(res, result) + 1;
        }
        if(map[y+1][x] == 1) {
            int result = dfs(map, visit, x, y+1, targetX, targetY);
            if(result != -1) res = Math.min(res, result) + 1;
        }
        if(res == 9999999) return -1;
        else return res;
    }
}

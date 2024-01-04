package 게임_맵_최단거리;

import java.util.LinkedList;
import java.util.Queue;

//https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
public class Solution {
    public static final int[][] maps = {
            {1,0,0,0,0,0},
            {1,0,1,1,1,1},
            {1,0,1,0,0,1},
            {1,0,1,1,0,1},
            {1,0,0,1,0,1},
            {1,1,1,1,0,1},
    };
    public int[][] move = {
            {1,0,-1,0},
            {0,1,0,-1}
    };

    class Space {
        int x;
        int y;
        int cost;
        Space(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public int solution(int[][] maps) {
        boolean[][] visitedMap = new boolean[maps.length][maps[0].length];
        Queue<Space> queue = new LinkedList<>();
        Space space = new Space(0,0,1);
        queue.add(space);
        while(!queue.isEmpty() && !isFinish(maps, space)) {
            space = queue.poll();
            for(int i=0; i<4; i++) {
                Space nextSpace = new Space(space.x + move[0][i], space.y + move[1][i], space.cost + 1);
                if(isMovable(maps, visitedMap, nextSpace)) queue.add(nextSpace);
            }
        }
        if(queue.isEmpty() && !isFinish(maps, space)) return -1;
        return space.cost;
    }

    public boolean isMovable(int[][] maps, boolean[][] visited, Space space) {
        try { int temp = maps[space.y][space.x]; } catch (ArrayIndexOutOfBoundsException e) { return false; }
        if(maps[space.y][space.x]==0) return false;
        if(visited[space.y][space.x]) return false;
        visited[space.y][space.x] = true;
        return true;
    }

    public boolean isFinish(int[][] maps, Space space) {
        if(maps.length - 1 == space.y && maps[0].length - 1 == space.x) return true;
        return false;
    }
}
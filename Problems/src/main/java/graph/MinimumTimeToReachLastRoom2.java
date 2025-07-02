package graph;

import java.util.PriorityQueue;

public class MinimumTimeToReachLastRoom2 {
    /*
    https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/
     */

    //Same as MinimumTimeToReachLastRoom1. Only difference is 'step'.
    int maxRows, maxCols;
    public int minTimeToReach(int[][] moveTime) {
        maxRows = moveTime.length-1;
        maxCols = moveTime[0].length-1;
        int[][] maxTime = new int[maxRows+1][maxCols+1];

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                maxTime[i][j] = Integer.MAX_VALUE;
            }
        }
        maxTime[0][0] = 0;

        int[][] directions =  new int[][]{{0,-1},{0,1},{-1,0},{1,0}};

        PriorityQueue<int[]> timeQueue = new PriorityQueue<int[]>((a, b) -> a[2]-b[2]);
        timeQueue.offer(new int[]{0,0,0,0}); //Index 0 and 1 are coordinates, index 2 is time till now, index 3 has steps.

        while(!timeQueue.isEmpty()) {
            int size = timeQueue.size();

            for(int i = 0; i < size; i++) {
                int[] curr = timeQueue.poll();
                int row = curr[0];
                int col = curr[1];
                int time = curr[2];
                int step = curr[3];

                if(row == maxRows && col == maxCols) {
                    return time;
                }

                for(int[] dir : directions) {
                    int newRow = curr[0]+dir[0];
                    int newCol = curr[1]+dir[1];
                    if(canTraverse(newRow, newCol)) {
                        int newTime = getTime(moveTime, step, newRow, newCol, time);
                        if(newTime < maxTime[newRow][newCol]) {
                            maxTime[newRow][newCol] = newTime;
                            timeQueue.offer(new int[]{newRow,newCol,newTime, step+1});
                        }
                    }
                }
            }
        }

        return 0;
    }

    public int getTime(int[][] moveTime, int step, int row, int col, int currTime) {
        int time = Math.max(moveTime[row][col], currTime);
        time += (step%2 == 0 ? 1 : 2);
        return time;
    }

    public boolean canTraverse(int row, int col) {
        if(row < 0 || col < 0) {
            return false;
        }
        if(row > maxRows || col > maxCols) {
            return false;
        }

        return true;
    }
}

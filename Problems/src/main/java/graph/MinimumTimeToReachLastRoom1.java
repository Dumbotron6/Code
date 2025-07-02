package graph;

import java.util.PriorityQueue;

public class MinimumTimeToReachLastRoom1 {
    /*
    https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/
     */

    /*
    We use Dijkstra's algorithm. Almost same as PathWithMinimumEffort.
    Remember, it's not time spent at each room, it's time to be spent before moving to that room.
    So if last room is 100, then 100 seconds need to have been spent before coming to that room.
    */
    int maxRows, maxCols;
    public int minTimeToReach(int[][] moveTime) {
        maxRows = moveTime.length-1;
        maxCols = moveTime[0].length-1;
        int[][] maxTime = new int[maxRows+1][maxCols+1];

        for(int i = 0; i <= maxRows; i++) { //Store max times to reach room, this will also act as visited.
            for(int j = 0; j <= maxCols; j++) {
                maxTime[i][j] = Integer.MAX_VALUE;
            }
        }
        maxTime[0][0] = 0;

        PriorityQueue<int[]> timeQueue = new PriorityQueue<int[]>((a, b) -> a[2]-b[2]);
        timeQueue.offer(new int[]{0, 0, 0}); //Index 0 and 1 are coordinates while 2 is the time till now.

        int[][] directions = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

        while(!timeQueue.isEmpty()) {
            int size = timeQueue.size();
            int i = 0;

            while(i < size) {
                int[] curr = timeQueue.poll();
                if(curr[0] == maxRows && curr[1] == maxCols) {
                    return maxTime[maxRows][maxCols];
                }

                for(int[] dir : directions) {
                    int row = curr[0]+dir[0];
                    int col = curr[1]+dir[1];

                    if(canTraverse(row, col)) {
                        int currTime = curr[2] + 1; //Time to each previous room now plus time to move.
                        int newTime = moveTime[row][col] + 1; //Time to reach this room plus time to move.
                        int totalTime = Math.max(currTime, newTime); //Total time to move from this room.
                        if(totalTime < maxTime[row][col]) { //Add only if total time is less than existing max time in the room
                            timeQueue.offer(new int[]{row, col, totalTime});
                            maxTime[row][col] = totalTime;
                        }
                    }
                }
                i++;
            }
        }

        return 0;
    }

    public boolean canTraverse(int i, int j) {
        if(i < 0 || j < 0) {
            return false;
        }
        if(i > maxRows || j > maxCols) {
            return false;
        }
        return true;
    }
}

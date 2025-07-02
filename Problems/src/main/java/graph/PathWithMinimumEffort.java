package graph;

import java.util.PriorityQueue;

public class PathWithMinimumEffort {
    /*
    https://leetcode.com/problems/path-with-minimum-effort/
     */

    //This uses Dijkstra's algorithm, only instead of and adjList, we have a matrix here.
    int maxRows, maxCols;
    boolean[][] visited;
    public int minimumEffortPath(int[][] heights) {
        maxRows = heights.length-1;
        maxCols = heights[0].length-1;
        visited = new boolean[maxRows+1][maxCols+1];

        PriorityQueue<int[]> pointEffort = new PriorityQueue<int[]>((a, b) -> a[2]-b[2]); //Sort by minimum of maximum efforts.
        pointEffort.offer(new int[]{0,0,0}); //Index 0 and 1 are coordinates, 2 is the maximum effort till this point.

        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        while(!pointEffort.isEmpty()) {
            int[] curr = pointEffort.poll();
            if(curr[0] == maxRows && curr[1] == maxCols) {
                return curr[2];
            }

            if(visited[curr[0]][curr[1]]) { //If visited, it means already reached via a lesser maximum effort.
                continue;
            }
            visited[curr[0]][curr[1]] = true;

            for(int[] dir : directions) {
                int newRow = curr[0] + dir[0];
                int newCol = curr[1] + dir[1];
                if(canTraverse(heights, newRow, newCol)) {
                    int height = heights[curr[0]][curr[1]]; //Height of current coordinate.
                    int newHeight = heights[newRow][newCol]; //Height of new coordinate.
                    pointEffort.offer(new int[]{newRow, newCol, Math.max(curr[2], Math.abs(height-newHeight))});
                }
            }
        }

        return 0;
    }

    public boolean canTraverse(int[][] heights, int row, int col) {
        if(row < 0 || col < 0) {
            return false;
        }
        if(row > maxRows || col > maxCols) {
            return false;
        }

        return true;
    }

    /*
    Building on previously submitted, we store max effort at each point instead of visited,
        and to queue only if new effort is less than old effort.
    The improvement is, we are avoiding adding to queue itself, which takes O(logN) time for each insert.
    */
    int[][] effort;
    public int minimumEffortPathAlt(int[][] heights) {
        maxRows = heights.length-1;
        maxCols = heights[0].length-1;
        effort = new int[maxRows+1][maxCols+1];

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                effort[i][j] = Integer.MAX_VALUE;
            }
        }
        effort[0][0] = 0;

        PriorityQueue<int[]> pointEffort = new PriorityQueue<int[]>((a,b) -> a[2]-b[2]); //Sort by minimum of maximum efforts.
        pointEffort.offer(new int[]{0,0,0}); //Index 0 and 1 are coordinates, 2 is the maximum effort till this point.

        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        while(!pointEffort.isEmpty()) {
            int[] curr = pointEffort.poll();
            if(curr[0] == maxRows && curr[1] == maxCols) {
                return curr[2];
            }


            for(int[] dir : directions) {
                int newRow = curr[0] + dir[0];
                int newCol = curr[1] + dir[1];
                if(canTraverse(heights, newRow, newCol)) {
                    int height = heights[curr[0]][curr[1]]; //Height of current coordinate.
                    int newHeight = heights[newRow][newCol]; //Height of new coordinate.
                    int newEffort = Math.max(curr[2], Math.abs(height-newHeight));
                    if(newEffort < effort[newRow][newCol]) {
                        effort[newRow][newCol] = newEffort;
                        pointEffort.offer(new int[]{newRow, newCol, newEffort});
                    }
                }
            }
        }

        return effort[maxRows][maxCols];
    }
}

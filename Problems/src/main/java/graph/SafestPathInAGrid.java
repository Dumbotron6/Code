package graph;

import java.util.*;

public class SafestPathInAGrid {
    /*
    https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/
     */

    /*
    This is actually a hard problem but tagged as a medium problem.
    This problem actually has two parts.
        1. Finding minimum distance from thieves(similar to RottenOranges) which uses BFS.
        2. Finding safest path among the minimum distance which uses Dijkstra's algorithm.
    */
    int maxRows, maxCols;
    int[][] directions;
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        maxRows = grid.size()-1;
        maxCols = grid.get(0).size()-1;
        directions = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};

        int[][] dist = minDistToThief(grid); //Find minimum distance.

        //Find safest path using Dijkstra's.
        PriorityQueue<int[]> safeQueue = new PriorityQueue<int[]>((a, b) -> b[0]-a[0]); //Sort by max distance from thief.
        safeQueue.offer(new int[]{dist[0][0], 0, 0}); //Has minimum distance till now in the path and coordinates.

        boolean[][] visited = new boolean[maxRows+1][maxCols+1];
        visited[0][0] = true;

        while(!safeQueue.isEmpty()) {
            int size = safeQueue.size();

            for(int i = 0; i < size; i++) {
                int[] curr = safeQueue.poll();
                int row = curr[1];
                int col = curr[2];
                int currDist = curr[0];
                if(row == maxRows && col == maxCols) {
                    return currDist;
                }

                for(int[] dir : directions) {
                    int newRow = row+dir[0];
                    int newCol = col+dir[1];

                    /*
                    We need to pick the minimum distance in the path we took.
                    Although we sort by maximum distance in the PriorityQueue, we may end up with a lower distance inevitably in the path.
                    So we pick the lowest of the two.
                    */
                    if(canTraverse(newRow, newCol) && !visited[newRow][newCol]) {
                        int newDist = Math.min(currDist, dist[newRow][newCol]);
                        safeQueue.offer(new int[]{newDist, newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
        }

        return 0;
    }

    //Use BFS to find minimum distance.
    public int[][] minDistToThief(List<List<Integer>> grid) {
        int[][] dist = new int[maxRows+1][maxCols+1];
        LinkedList<int[]> safeTracker = new LinkedList<int[]>();

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(grid.get(i).get(j) == 1) {
                    safeTracker.add(new int[]{i,j});
                }else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while(!safeTracker.isEmpty()) {
            int size = safeTracker.size();

            for(int i = 0; i < size; i++) {
                int[] curr = safeTracker.pop();
                int row = curr[0];
                int col = curr[1];
                int currDist = dist[row][col];

                for(int[] dir : directions) {
                    int newRow = row+dir[0];
                    int newCol = col+dir[1];
                    if(canTraverse(newRow, newCol)) {
                        int newDist = currDist+1;
                        if(newDist < dist[newRow][newCol]) {
                            dist[newRow][newCol] = newDist;
                            safeTracker.add(new int[]{newRow, newCol});
                        }
                    }
                }
            }
        }

        return dist;
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

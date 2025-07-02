package graph;

import java.util.LinkedList;

public class NumberOfEnclaves {
    /*
    https://leetcode.com/problems/number-of-enclaves/
     */

    /*
    Almost same problem as PacificAtlanticWaterOverflow and SurroundRegions.
    Only, instead of DFS, we use BFS, We can also use DFS.
     */

    int maxRows, maxCols;
    public int numEnclaves(int[][] grid) {
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;
        LinkedList<int[]> queue = new LinkedList<int[]>();

        int enclaves = 0;

        for(int i = 0; i <= maxRows; i++) {
            if(grid[i][0] == 1) {
                queue.add(new int[]{i, 0});
                grid[i][0] = 0;
            }
            if(grid[i][maxCols] == 1) {
                queue.add(new int[]{i, maxCols});
                grid[i][maxCols] = 0;
            }
        }

        for(int i = 0; i <= maxCols; i++) {
            if(grid[0][i] == 1) {
                queue.add(new int[]{0, i});
                grid[0][i] = 0;
            }
            if(grid[maxRows][i] == 1) {
                queue.add(new int[]{maxRows, i});
                grid[maxRows][i] = 0;
            }
        }

        int[][] directions = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};

        while(!queue.isEmpty()) {
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++) {
                int[] curr = queue.pop();

                for(int[] dir : directions) {
                    int newRow = curr[0]+dir[0];
                    int newCol = curr[1]+dir[1];
                    if(canTraverse(grid, newRow, newCol)) {
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(grid[i][j] == 1) {
                    enclaves++;
                }
            }
        }

        return enclaves;
    }

    public boolean canTraverse(int[][] grid, int row, int col) {
        if(row < 0 || col < 0) {
            return false;
        }
        if(row > maxRows || col > maxCols) {
            return false;
        }
        if(grid[row][col] == 0) {
            return false;
        }
        grid[row][col] = 0;

        return true;
    }
}

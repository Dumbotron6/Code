package graph;

import java.util.LinkedList;

public class RottenOranges {
    /*
    https://leetcode.com/problems/rotting-oranges/
     */

    /*
    Since all adjacent fresh oranges will rotten per minute, it means we traverse all oranges of that level together.
        ie, For second minute, all oranges at second minute need to be checked.
    So we use BFS.
    See ShortestPathInBinaryMatrix first to understand BFS.
    Since there are multiple sources in the beginning(rotten oranges), this is called a multi-source BFS.
    */
    int maxRows, maxCols;
    public int orangesRotting(int[][] grid) {
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;

        LinkedList<int[]> queue = new LinkedList<int[]>();
        int minutes = 0;
        int fresh = 0;

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(grid[i][j] == 2) { //Traverse through every grid point, looking for a rotten orange.
                    queue.add(new int[]{i,j}); //This will give 0 minute rotten oranges.
                }
                if(grid[i][j] == 1) { //Count initial fresh oranges.
                    fresh++;
                }
            }
        }

        //All the directions we can traverse from a grid point- up, left, down and right.
        int[][] directions = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
        while(!queue.isEmpty()) {
            int qSize = queue.size();
            int s = 0;
            while(s < qSize) {
                int[] current = queue.pop();
                for(int[] dir : directions) { //From the rotten orange, check every direction for a fresh orange.
                    int checkRow = current[0]+dir[0];
                    int checkCol = current[1]+dir[1];
                    if(hasFresh(grid, checkRow, checkCol)) { //If fresh is found, make it rotten and add to queue.
                        fresh--; //Decrease fresh count.
                        queue.add(new int[]{checkRow, checkCol});
                    }
                }
                s++;
            }
            if(!queue.isEmpty()) { //Added to queue only if fresh orange found, so if not empty, means fresh was rotted.
                minutes++;
            } //If empty, it means no adjacent fresh was found, so no minute increase.
        }

        if(fresh > 0) { //Check if there is any fresh orange left. Can happen if it was isolated.
            return -1;
        }

        return minutes;
    }

    public boolean hasFresh(int[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row > maxRows || col > maxCols) {
            return false;
        }
        if(grid[row][col] == 1) { //If fresh is found, make it rotten.
            grid[row][col] = 2;
            return true;
        }
        return false; //Already rotten or no orange in the spot.
    }
}

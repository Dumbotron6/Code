package graph;

public class NumberOfIslands {
    /*
    https://leetcode.com/problems/number-of-islands/description/
     */

    int maxRows, maxCols;
    //This is done using DFS.
    public int numIslands(char[][] grid) {
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;
        int islands = 0;

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(grid[i][j] == '1') { //Go through every land, mark all connected land using landMarker.
                    islands++;
                    landMarker(grid, i, j);
                }
            }
        }

        return islands;
    }

    public void landMarker(char[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row > maxRows || col > maxCols) {
            return;
        }
        if(grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0'; //Mark every piece of land we visit. Now '0' means its water or this land has already been counted.

        landMarker(grid, row-1, col);
        landMarker(grid, row+1, col);
        landMarker(grid, row, col-1);
        landMarker(grid, row, col+1);
    }
}

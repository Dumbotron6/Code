package graph;

public class IslandPerimeter {
    /*
    https://leetcode.com/problems/island-perimeter/
     */

    int maxRows = 0;
    int maxCols = 0;
    public int islandPerimeter(int[][] grid) {
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;
        int startRow = 0, startCol = 0;
        for(int i = 0; i <= maxRows; i++) { //Find the starting point.
            for(int j = 0; j <= maxCols; j++) {
                if(grid[i][j] == 1) {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }
        return traverse(grid, startRow, startCol);
    }

    public int traverse(int[][] grid, int row, int col) {
        if(row < 0 || col < 0) { //The edges of the matrix is considered a side of the land facing water.
            return 1;
        }
        if(row > maxRows || col > maxCols) {
            return 1;
        }
        if(grid[row][col] < 0) { //This spot is already visited.
            return 0;
        }
        if(grid[row][col] == 0) { //If the spot is water, then we arrived here from land, so a side of the land is facing water.
            return 1;
        }
        grid[row][col] = -1; //If we visited this spot, we mark it.

        int sides = 0;

        sides += traverse(grid, row+1, col);
        sides += traverse(grid, row-1, col);
        sides += traverse(grid, row, col+1);
        sides += traverse(grid, row, col-1);

        return sides;
    }

    public int islandPerimeterAlt(int[][] grid) {
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;
        int sides = 0;
        for(int row = 0; row <= maxRows; row++) {
            for(int col = 0; col <= maxCols; col++) {
                if(grid[row][col] == 1) {
                    if(row == 0 || grid[row-1][col] == 0) {
                        sides++;
                    }
                    if(col == 0 || grid[row][col-1] == 0) {
                        sides++;
                    }
                    if(row == maxRows || grid[row+1][col] == 0) {
                        sides++;
                    }
                    if(col == maxCols || grid[row][col+1] == 0) {
                        sides++;
                    }
                }
            }
        }
        return sides;
    }
}

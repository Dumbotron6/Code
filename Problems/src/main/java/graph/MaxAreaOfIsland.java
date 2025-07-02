package graph;

public class MaxAreaOfIsland {
    /*
    https://leetcode.com/problems/max-area-of-island/description/
     */

    /*
    Exactly the same as NumberOfIslands. Only instead of counting the number of islands,
        we count the area.
    */
    int maxRows, maxCols;
    public int maxAreaOfIsland(int[][] grid) {
        maxRows = grid.length-1;
        maxCols = grid[0].length-1;
        int maxArea = 0;

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                maxArea = Math.max(maxArea, landMarker(grid, i, j));
            }
        }

        return maxArea;
    }

    public int landMarker(int[][] grid, int row, int col) {
        if(row < 0 || col < 0 || row > maxRows || col > maxCols) {
            return 0;
        }
        if(grid[row][col] == 0) { //When water is reached or land already counted.
            return 0;
        }

        grid[row][col] = 0; //Marking when land already counted.
        int area = 1;

        //Recursively mark every adjacent land and add it area.
        area += landMarker(grid, row+1, col);
        area += landMarker(grid, row-1, col);
        area += landMarker(grid, row, col+1);
        area += landMarker(grid, row, col-1);

        return area;
    }
}

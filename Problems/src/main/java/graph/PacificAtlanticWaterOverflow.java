package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterOverflow {
    /*
    https://leetcode.com/problems/pacific-atlantic-water-flow/description/
     */

    //This uses DFS, as we go as deep as we can from each point.
    int maxRows, maxCols;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        maxRows = heights.length-1;
        maxCols = heights[0].length-1;

        boolean[][] pacific = new boolean[maxRows+1][maxCols+1]; //Map for whether point can be reached from pacific.
        boolean[][] atlantic = new boolean[maxRows+1][maxCols+1]; //Map for whether point can be reached from atlantic.

        List<List<Integer>> highPoints = new ArrayList<List<Integer>>();

        /*
        Instead of checking whether each point can reach both oceans, we check if each point can be reached from the ocean,
            ie, we check in reverse. So top and left, we check pacific, bottom and right we check atlantic.
        We maintain two maps, one for pacific and the other for atlantic. The common ones are the ones are the ones that can reach both oceans.
        */
        for(int i = 0; i <= maxRows; i++) {
            canReachOcean(i, 0, heights, -1, pacific);
            canReachOcean(i, maxCols, heights, -1, atlantic);
        }
        for(int i = 0; i <= maxCols; i++) {
            canReachOcean(0, i, heights, -1, pacific);
            canReachOcean(maxRows, i, heights, -1, atlantic);
        }

        //Check for common points.
        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    highPoints.add(Arrays.asList(i,j));
                }
            }
        }

        return highPoints;
    }

    public void canReachOcean(int row, int col, int[][] heights, int prevVal, boolean[][] ocean) {
        if(row < 0 || col < 0) {
            return;
        }
        if(row > maxRows || col > maxCols) {
            return;
        }
        if(ocean[row][col]) { //If this point was reached in any other path and was already decided as yes.
            return;
        }//We don't check for false because one path may give false while another might give true.

        if(heights[row][col] >= prevVal) { //Can be reached only if height is >= previous point, ie, reverse of problem statement.
            ocean[row][col] = true;
            canReachOcean(row-1 ,col, heights, heights[row][col], ocean);
            canReachOcean(row+1, col, heights, heights[row][col], ocean);
            canReachOcean(row, col-1, heights, heights[row][col], ocean);
            canReachOcean(row, col+1, heights, heights[row][col], ocean);
        }
    }
}

package graph;

import java.util.LinkedList;

public class ZeroOneMatrix {
    /*
    https://leetcode.com/problems/01-matrix/description/
     */

    /*
    Same problem as RottenOranges, except this time, we have to return time at each point for it to turn rotten.
    'Return the distance of the nearest 0 for each cell.' is the problem statement. This will be lead us to think of different approach.
    But think of it in reverse. From each 0, see if a 1 can be reached and if so, what is the shortest distance for that 1.
    There are multiple sources in the beginning, ie, multiple 0s.
    */
    int maxRows, maxCols;
    public int[][] updateMatrix(int[][] mat) {
        maxRows = mat.length-1;
        maxCols = mat[0].length-1;
        int dist = 0;

        LinkedList<int[]> queue = new LinkedList<int[]>();
        int[][] result = new int[maxRows+1][maxCols+1];

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(mat[i][j] == 0) {
                    queue.add(new int[]{i,j}); //Add all current 0s.
                }
            }
        }

        int[][] directions = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
        while(!queue.isEmpty()) {
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++) {
                int[] curr = queue.pop();
                result[curr[0]][curr[1]] = dist;

                for(int[] dir : directions) { //From 0, check every direction for a 1.
                    int newRow = curr[0] + dir[0];
                    int newCol = curr[1] + dir[1];
                    if(canTraverse(mat, newRow, newCol)) { //Add if not already 0 or if not already reached by a 0.
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
            dist++;
            //If queue isn't empty after this, it means there are 1s yet to be reached after first 0 loop. So distance from the initial 0s will increase.
        }

        return result;
    }

    public boolean canTraverse(int[][] mat, int row, int col) {
        if(row < 0 || col < 0) {
            return false;
        }
        if(row > maxRows || col > maxCols) {
            return false;
        }

        if(mat[row][col] == 0) {
            return false;
        }
        mat[row][col] = 0; //Mark so we know it is reached by a 0, ie, the shortest distance from 0 is already calculated.

        return true;
    }
}

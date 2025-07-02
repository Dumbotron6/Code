package matrix;

import java.util.Arrays;

public class MakeUnivalueGrid {
    /*

     */

    public int minOperations(int[][] grid, int x) {
        int m = grid.length;
        int n = grid[0].length;
        int[] sortedArray = new int[m*n];
        int k = 0;
        int rem = grid[0][0]%x;

        //If the remainder of all the nums/x are not equal, then they can never become the same.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j]%x != rem) {
                    return -1;
                }
                sortedArray[k++] = grid[i][j];
            }
        }

        Arrays.sort(sortedArray);

        /*
        Median of all the nums is the num is the one all nums can reach shortest.
        Taking array [2,4,6,8], median can be 4 or 6. Reaching both will take 4 steps,
            so it doesn't matter which one we pick.
        */
        int median = sortedArray[sortedArray.length/2];
        int steps = 0;

        for(int ele : sortedArray) {
            steps += Math.abs((median-ele)/x);
        }

        return steps;
    }
}

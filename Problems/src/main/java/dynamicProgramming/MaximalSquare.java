package dynamicProgramming;

public class MaximalSquare {
    /*
    https://leetcode.com/problems/maximal-square/description/
     */

    /*
    From the bottom right, we consider that point as the top left of the square and if so, what the maxSq will be.
    For example, a sq surrounded by 0 on 3 (on right, down and diag) sides will have maxSq 1.
    A sq surrounded by 1 on 3 sides will have maxSq 2.
    A sq can have max area only as large as its sides. That's why we do 1+Math.min.
    */
    int row, col;
    public int maximalSquare(char[][] matrix) {
        row = matrix.length-1;
        col = matrix[0].length-1;
        int m = row, n = col;
        Integer[][] cache = new Integer[m+1][n+1];

        int maxSq = 0;

        for(int i = m; i >= 0; i--) {
            for(int j = col; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    int right = checkCache(cache, i, j+1);
                    int down = checkCache(cache, i+1, j);
                    int diag = checkCache(cache, i+1, j+1);
                    cache[i][j] = 1+Math.min(Math.min(right, down), diag);
                }else {
                    cache[i][j] = 0;
                }
                maxSq = Math.max(maxSq, cache[i][j]);
            }
            m--;
        }

        for(int i = m; i >= 0; i--) {
            int j = 0;
            if(matrix[i][j] == '1') {
                int right = checkCache(cache, i, j+1);
                int down = checkCache(cache, i+1, j);
                int diag = checkCache(cache, i+1, j+1);
                cache[i][j] = 1+Math.min(Math.min(right, down), diag);
            }else {
                cache[i][j] = 0;
            }
            maxSq = Math.max(maxSq, cache[i][j]);
        }

        return maxSq*maxSq;
    }

    public int checkCache(Integer[][] cache, int i, int j) {
        if(i > row || j > col) {
            return 0;
        }
        if(cache[i][j] != null) {
            return cache[i][j];
        }
        return 1;
    }
}

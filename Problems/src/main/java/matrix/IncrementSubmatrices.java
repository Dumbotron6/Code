package matrix;

public class IncrementSubmatrices {
    /*
    https://leetcode.com/problems/increment-submatrices-by-one/
     */

    /*
    There is a more optimised solution for this. Check that.
    We use the technique called 'Line Sweep' or 'Difference Array'.
    This technique is already used in ZeroArrayTransformation1 and ZeroArrayTransformation2.
    It's used in an array in the above two problems.It's expanded to cover a 2D array in this problem.
    It's expanded by doing the + and - on every row, because multiple queries can cover same rows but not others,
        thereby leaving one row and col with one value but another row and col with another value.
    */
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] result = new int[n][n];

        for(int[] query : queries) {
            int fromCol = query[1];
            int toCol = query[3]+1;
            int toRow = query[2];
            for(int i = query[0]; i <= toRow; i++) {
                result[i][fromCol]++;
                if(toCol < n) {
                    result[i][toCol]--;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < n; j++) {
                result[i][j] += result[i][j-1];
            }
        }

        return result;
    }
}

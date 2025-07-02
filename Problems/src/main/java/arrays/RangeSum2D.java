package arrays;

public class RangeSum2D {
    /*
    https://leetcode.com/problems/range-sum-query-2d-immutable/description/
     */

    class NumMatrix {

        int prefixSums[][];

        public NumMatrix(int[][] matrix) {
            int row = matrix.length+1;
            int col = matrix[0].length+1;
            prefixSums = new int[row][col];
            int sum = 0;

            /*
            We are storing the sum of the matrix from top left to bottom right at any given point.
            For example, [2,3] will have sum of elements from [0,0] to [2,3].
            We have row and col as +1, so we can store 0 in first rows and cols which makes summing easier.
            */
            for(int i = 1; i < row; i++) {
                for(int j = 1; j < col; j++) {
                    prefixSums[i][j] = matrix[i-1][j-1]+prefixSums[i-1][j]; //above
                    prefixSums[i][j] += prefixSums[i][j-1]; //left
                    prefixSums[i][j] -= prefixSums[i-1][j-1]; //Remove common.
                }
            }
            /*
            The sum of matrix at any given point is sum of matrix[till previous row] and matrix[till previous col].
            The problem is, both of those will common elements, which is prefixSums[i-1][j-1] which will occur in both
                matrix[till previous row] and matrix[till previous col] so it's counted twice.
            So we remove it once as above.
            Alternate and easier below.
            */
            /*
            for(int i = 1; i < row; i++) {
                int prefix = 0;
                for(int j = 1; j < col; j++) {
                    prefix += matrix[i-1][j-1]
                    int above = prefixSums[i-1][j];
                    prefixSums[i][j] -= prefix+above;
                }
            }
             */
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int sum = prefixSums[row2+1][col2+1] - prefixSums[row1][col2+1] - prefixSums[row2+1][col1];
            sum += prefixSums[row1][col1]; //Add back matrix removed twice.
        /*
        We take matrix sum till bottom right, subtract col2-1 matrix, and row1-1 matrix.
        But both will have common matrix [row1-1] and [col1-1] which we would've subtracted twice.
        So we need to add it back once.
        */

            return sum;
        }

        /*
        Matrix
        [3, 0, 1, 4, 2]
        [5, 6, 3, 2, 1]
        [1, 2, 0, 1, 5]
        [4, 1, 0, 1, 7]
        [1, 0, 3, 0, 5]

        PrefixSum
        [0, 0, 0, 0, 0, 0]
        [0, 3, 3, 4, 8, 10]
        [0, 8, 14, 18, 24, 27]
        [0, 9, 17, 21, 28, 36]
        [0, 13, 22, 26, 34, 49]
        [0, 14, 23, 30, 38, 58]
        */
    }
}

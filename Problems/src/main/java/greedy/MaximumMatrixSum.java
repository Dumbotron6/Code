package greedy;

public class MaximumMatrixSum {
    /*
    https://leetcode.com/problems/maximum-matrix-sum/
     */

    /*
    When we keep flipping, only one element max will be left negative in the end(if there are odd negatives).
    So we assign that to the smallest number.
    */
    public long maxMatrixSum(int[][] matrix) {
        int minNum = Integer.MAX_VALUE;
        long total = 0;
        int neg = 0;

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                int val = Math.abs(matrix[i][j]);
                minNum = Math.min(minNum, val);
                if(matrix[i][j] < 0) {
                    neg++;
                }
                total += val;
            }
        }

        if(neg%2 == 1) {
            total -= 2*minNum;
        }

        return total;
    }
}

package matrix;

public class SearchInA2DMatrix2 {
    /*
    https://leetcode.com/problems/search-a-2d-matrix-ii/description/
     */

    //The time complexity is O(m+n) as we move that many times at worst.
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length-1;
        int n = matrix[0].length-1;
        int row = 0;
        int col = n;

        //At each step, we eliminate either a row or a column.
        while(row <= m && col >= 0) {
            if(matrix[row][col] == target) {
                return true;
            }else if(matrix[row][col] > target) {
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    /*
    [1,4,7,11,15],
    [2,5,8,12,19],
    [3,6,9,16,22],
    [10,13,14,17,24],
    [18,21,23,26,30]

    Take this example, the target is 14. We start at top right.
    15 is greater than 14, due to ascending nature of the column values, we know nth column can be eliminated so we do col-1;
    Now for [0,3], 12 is smaller than 14, due to ascending nature of row values, we know 0th row can be eliminated so we do row+1;
    ie, nothing prior to 12 can ever be used to find 14, so we can eliminate that row.
    So at each step, we eliminate either a row or a column and keep moving inward to find the target.
    */
}

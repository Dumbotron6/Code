package binarySearch;

public class SearchInA2DMatrix {
    /*
    https://leetcode.com/problems/search-a-2d-matrix/description/
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length-1;
        int cols = matrix[0].length-1;

        int left = 0, right = rows;
        //Binary search to find row with least greatest num close to target.
        while(left <= right) {
            int mid = left+(right-left)/2;
            if(matrix[mid][cols] == target) {
                return true;
            }else if(matrix[mid][cols] < target) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        //If no row is greater than target.
        if(left > rows) {
            return false;
        }
        int searchRow = left;
        left = 0; right = cols;

        //Search the row for the target.
        while(left <= right) {
            int mid = left+(right-left)/2;
            if(matrix[searchRow][mid] == target) {
                return true;
            }else if(matrix[searchRow][mid] < target) {
                left = mid+1;
            }else {
                right = mid-1;
            }
        }

        return false;
    }
}

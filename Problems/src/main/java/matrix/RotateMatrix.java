package matrix;

public class RotateMatrix {
    /*
    https://leetcode.com/problems/rotate-image/description/
     */

    public void rotate(int[][] matrix) {

        int n = matrix.length - 1;
        for(int i = 0; i <= n; i++) {
            for(int j = i+1; j <= n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j];
                matrix[i][n-j] = temp;
            }
        }
    }
}

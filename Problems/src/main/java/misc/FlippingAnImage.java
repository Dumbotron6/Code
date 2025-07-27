package misc;

public class FlippingAnImage {
    /*
    https://leetcode.com/problems/flipping-an-image/
     */

    public int[][] flipAndInvertImage(int[][] image) {
        int rows = image.length-1;
        int cols = image[0].length-1;

        for(int i = 0; i <= rows; i++) {
            int left = 0, right = cols;
            while(left <= right) {
                int leftVal = image[i][left];
                int rightVal = image[i][right];
                image[i][right--] = leftVal == 0 ? 1 : 0;
                image[i][left++] = rightVal == 0 ? 1 : 0;
            }
        }

        return image;
    }
}

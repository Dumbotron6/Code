package graph;

public class FloodFill {
    /*
    https://leetcode.com/problems/flood-fill/
     */

    int startCol, maxI, maxJ;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        startCol = image[sr][sc];
        maxI = image.length-1;
        maxJ = image[0].length-1;

        dfs(image, color, sr, sc);

        return image;
    }

    public void dfs(int[][] image, int targetCol, int i, int j) {
        if(i < 0 || j < 0) {
            return;
        }
        if(i > maxI || j > maxJ) {
            return;
        }
        if(image[i][j] != startCol || image[i][j] == targetCol) { //Initial color incorrect or already changed color.
            return;
        }
        image[i][j] = targetCol;

        dfs(image, targetCol, i-1, j);
        dfs(image, targetCol, i+1, j);
        dfs(image, targetCol, i, j-1);
        dfs(image, targetCol, i, j+1);
    }
}

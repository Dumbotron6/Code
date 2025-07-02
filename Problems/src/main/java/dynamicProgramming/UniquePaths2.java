package dynamicProgramming;

public class UniquePaths2 {
    /*
    https://leetcode.com/problems/unique-paths-ii/description/
     */

    int maxRow;
    int maxCol;
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        maxRow = obstacleGrid.length;
        maxCol = obstacleGrid[0].length;

        if(obstacleGrid[maxRow-1][maxCol-1] == 1) { //Obstacle at end point.
            return 0;
        }

        Integer[][] dp = new Integer[maxRow][maxCol];
        dp[maxRow-1][maxCol-1] = 1;

        return getPaths(obstacleGrid, 0, 0, dp);
    }

    /*
    We use DP to keep track of the number of ways we can reach the end from that point.
    This lets us avoid recalculating same paths.
    */
    public int getPaths(int[][] obstacleGrid, int row, int col, Integer[][] dp) {
        if(row >= maxRow || col >= maxCol) {
            return 0;
        }

        if(dp[row][col] != null) {
            return dp[row][col];
        }

        if(obstacleGrid[row][col] == 1) {
            dp[row][col] = 0;
            return 0;
        }

        //Head down and head right.
        int ways = getPaths(obstacleGrid, row+1, col, dp) + getPaths(obstacleGrid, row, col+1, dp);
        dp[row][col] = ways;

        return ways;
    }
}

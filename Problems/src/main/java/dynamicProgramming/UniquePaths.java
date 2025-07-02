package dynamicProgramming;

public class UniquePaths {
    /*
    https://leetcode.com/problems/unique-paths/
     */

    public int uniquePaths(int m, int n) {
        Integer[][] tracker = new Integer[m][n];
        return getPaths(0, 0, tracker, m-1, n-1);
    }

    public int getPaths(int i, int j, Integer[][] tracker, int m, int n) {
        //When right bottom is reached, return 2.
        if(i == m && j == n) {
            return 1;
        }
        if(i > m || j > n) {
            return 0;
        }

        if(tracker[i][j] != null) {
            return tracker[i][j];
        }
        /*
        What does this mean?
        Robot can take right or bottom path. Say we are on the tile just before the last one.
        We can go right or down. If we reach the last tile, 1 will be returned.
        Say we are on some other tile. If by going right, we reach last tile,
            and by going down we reach last tile, it means we have two ways to reach the last tile
            from current tile.
            [0,10,4,1]
            [10,6,3,1]
            [4,3,2,1]
            [1,1,1,0]
        The above matrix is the result of m == 4 and n == 4. This gives us 20.
        When i==2 and j==2, we can get to last tile by going right or down. So value is 2.
        When i==1 and j==2, by going right, we have two ways to reach last tile.
            By going down, we have 1 way.
        So the sum is 3. So while backtracking, we store those values in the tracker.
        This is the DP approach.
        */
        tracker[i][j] = getPaths(i+1, j, tracker, m, n) + getPaths(i, j+1, tracker, m, n);
        return tracker[i][j];
    }
}

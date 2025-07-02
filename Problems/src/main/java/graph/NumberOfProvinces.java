package graph;

public class NumberOfProvinces {
    /*
    https://leetcode.com/problems/number-of-provinces/
     */

    /*
    Goal is to find independent provinces. If there is a 10x10 matrix, there can be max 10 provinces.
    If once province is connected to another, we have to see if that province is connected to another.
    Ex, If 1 is connected to 2, we have to check if 2 is connected to 3,etc.
    If yes, if 1 is connected to 3, w have already visited and checked 3 via 2, and determined it is not independent.
    So all unvisited i in findCircleNum is a province.
    */
    int maxVerti;
    boolean[] visited;
    public int findCircleNum(int[][] isConnected) {
        maxVerti = isConnected.length-1;
        visited = new boolean[maxVerti+1];
        int province = 0;

        for(int i = 0; i <= maxVerti; i++) {
            if(!visited[i]) {
                province++;
                dfs(isConnected, i);
            }
        }

        return province;
    }

    public void dfs(int[][] isConnected, int i) {
        if(visited[i]) {
            return;
        }
        visited[i] = true;

        for(int j = 0; j <= maxVerti; j++) {
            if(isConnected[i][j] == 1) {
                dfs(isConnected, j);
            }
        }
    }
}

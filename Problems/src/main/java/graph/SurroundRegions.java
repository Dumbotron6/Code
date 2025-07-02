package graph;

public class SurroundRegions {
    /*
    https://leetcode.com/problems/surrounded-regions/
     */

    /*
    This is almost the same as PacificAtlanticWaterOverflow.
    Instead of checking if any O can reach the perimeter, we check if O can be reached from the perimeter.
    We use DFS as we go as deep as we can from each O.
    */
    int maxRows, maxCols;
    public void solve(char[][] board) {
        maxRows = board.length-1;
        maxCols = board[0].length-1;

        boolean[][] connected = new boolean[maxRows+1][maxCols+1];

        for(int i = 0; i <= maxRows; i++) {
            dfs(board, i, 0, connected);
            dfs(board, i, maxCols, connected);
        }

        for(int i = 0; i <= maxCols; i++) {
            dfs(board, 0, i, connected);
            dfs(board, maxRows, i, connected);
        }

        for(int i = 0; i <= maxRows; i++) {
            for(int j = 0; j <= maxCols; j++) {
                if(!connected[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int row, int col, boolean[][] connected) {
        if(row < 0 || col < 0) {
            return;
        }
        if(row > maxRows || col > maxCols) {
            return;
        }
        if(connected[row][col]) {
            return;
        }

        if(board[row][col] == 'O') {
            connected[row][col] = true;
            dfs(board, row-1, col, connected);
            dfs(board, row+1, col, connected);
            dfs(board, row, col-1, connected);
            dfs(board, row, col+1, connected);
        }
    }
}

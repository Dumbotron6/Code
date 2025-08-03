package graph;

public class BattleshipsInABoard {
    /*
    https://leetcode.com/problems/battleships-in-a-board/description/
     */

    int row, col;
    public int countBattleships(char[][] board) {
        row = board.length-1;
        col = board[0].length-1;

        int ships = 0;

        for(int i = 0; i <= row; i++) {
            for(int j = 0; j <= col; j++) {
                if(board[i][j] == 'X') {
                    ships++;
                    removeShip(board, i, j);
                }
            }
        }

        return ships;
    }

    public void removeShip(char[][] board, int i, int j) {
        if(i == row || j == col) {
            return;
        }
        if(board[i][j] == '.') {
            return;
        }

        board[i][j] = '.';
        removeShip(board, i+1, j);
        removeShip(board, i, j+1);
    }
}

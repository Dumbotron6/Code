package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueen {
    /*
    https://leetcode.com/problems/n-queens/description/
     */

    /*
    We create a 2D array(matrix) to keep track of the queens placed. Positions without queens are marked with '.'
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> finalBoard = new ArrayList<List<String>>();
        char[][] temp = new char[n][n];

        for(int i = 0; i < n; i++)
            Arrays.fill(temp[i],'.');

        placeQueens(0, 0, n, finalBoard, temp);
        return finalBoard;
    }

    /*
    We first place a queen at a position and then use recursion to check combinations for that position.
    Similar to WordSearch, we check in the next position.
    Whereas in WordSearch we looked for the nearest indices, here we check the entire row, column, and diagonal to see
        if queen exists.
     */
    public void placeQueens(int row, int col, int n, List<List<String>> finalBoard, char[][] temp) {
        if(row == n) {
            finalBoard.add(addString(temp));
            return;
        }

        for(int j = 0; j < n && row < n; j++) {
            if(!queenExists(row, j, n, temp)) {
                temp[row][j] = 'Q';
                placeQueens(row+1, j+1, n, finalBoard, temp);
                temp[row][j] = '.';
            }
        }
    }

    public boolean queenExists(int row, int column, int n, char[][] temp) {
        //Checking previous rows.
        for(int i = row; i >= 0; i--) {
            if(temp[i][column] == 'Q')
                return true;
        }
        //Checking left upper diagonal.
        for(int i = row-1, j = column-1; i >= 0 && j >= 0; i--, j--) {
            if(temp[i][j] == 'Q')
                return true;
        }
        //Checking right upper diagonal.
        for(int i = row-1, j = column+1; i >= 0 && j < n; i--, j++) {
            if(temp[i][j] == 'Q')
                return true;
        }
        //The reason we don't check below rows, next columns or below diagonals are because we are iterating sequentially.
        //The next one would anyway have '.' so there's no reason to check.
        //Same for previous columns. Previous columns in the same row would be '.' as iteration happens like (i+1,j+1).
        //ie, if it goes to next row, it goes to next column also.
        return false;
    }

    public List<String> addString(char[][] temp) {
        List<String> str = new ArrayList<String>();
        for(int i = 0; i < temp.length; i++) {
            str.add(new String(temp[i]));
        }
        return str;
    }
}

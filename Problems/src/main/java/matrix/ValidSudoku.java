package matrix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidSudoku {
    /*
    https://leetcode.com/problems/valid-sudoku/description/
     */

    public boolean isValidSudoku(char[][] board) {
        int[][] trackerRow = new int[10][10];
        int[][] trackerCol = new int[10][10];
        Map<String, Set<Integer>> trackerBox = new HashMap<String, Set<Integer>>();

        /*
        trackerRow is used to keep track whether that number appeared in that row. Same for trackerColumn.
        There is no numerical formula for divinding the matrix by box(box 1, box 2.etc). So we use a HashMap instead to identify the boxes.
        */
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    String box = i/3 + "-" + j/3;
                    trackerBox.putIfAbsent(box, new HashSet<Integer>());
                    if(trackerRow[i][val] == 1 || trackerCol[val][j] == 1 || trackerBox.get(box).contains(val)) {
                        return false;
                    }
                    trackerRow[i][val] = 1;
                    trackerCol[val][j] = 1;
                    trackerBox.get(box).add(val);
                }
            }
        }
        return true;
    }
}

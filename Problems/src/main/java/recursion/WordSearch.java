package recursion;

public class WordSearch {
    /*
    https://leetcode.com/problems/word-search/description/
     */

    public boolean exist(char[][] board, String word) {
        return findWord(board, word);
    }

    /*
    There's a change to the usual backtracking done. We change the character we are at to a number, say '1', so that
        we don't visit the character again. For example, if the word is aba, and the matrix is simply [a,b,b],
        we change 'a' to '1' so that  in findNext, we don't go back to 'a' after ['a','b'] - because the matrix would now
        be ['1','b','b'].
    Meaning, we keep track of visited characters.
     */
    public boolean findWord(char[][] board, String word) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    board[i][j] = '1';
                    if(findNext(board, word, 1, i, j))
                        return true;
                    board[i][j] = word.charAt(0);
                }
            }
        }
        return false;
    }

    //We check all the nearest indices(or boxes). ie, up, down, left and right.
    public boolean findNext(char[][] board, String word, int index, int row, int column) {
        if(index > word.length()-1)
            return true;
        boolean found = false;
        int i = row-1;
        int j = column;
        if(!found && i >= 0 && i < board.length && word.charAt(index) == board[i][j]) {
            board[i][j] = '1';
            found = findNext(board, word, index+1, i, j);
            board[i][j] = word.charAt(index);
        }
        i = row + 1;
        if(!found && i >= 0 && i < board.length && word.charAt(index) == board[i][j]) {
            board[i][j] = '1';
            found = findNext(board, word, index+1, i, j);
            board[i][j] = word.charAt(index);
        }
        i = row;
        j = column-1;
        if(!found && j >= 0 && j < board[0].length && word.charAt(index) == board[i][j]) {
            board[i][j] = '1';
            found = findNext(board, word, index+1, i, j);
            board[i][j] = word.charAt(index);
        }
        j = column+1;
        if(!found && j >= 0 && j < board[0].length && word.charAt(index) == board[i][j]) {
            board[i][j] = '1';
            found = findNext(board, word, index+1, i, j);
            board[i][j] = word.charAt(index);
        }
        return found;
    }
}

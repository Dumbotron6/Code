package trie;

import java.util.ArrayList;
import java.util.List;

public class WordSearch2Alt {
    /*
    https://leetcode.com/problems/word-search-ii/
     */

    /*
    Building on previously submitted solution, we eliminate a few additional checks and variables.
    Namely, by using a string in the trie instead of a boolean, we dual-purpose it for both storing the word and eliminating duplicates.
     */
    TrieNode head;
    List<String> foundWords;
    int maxRows, maxCols;
    public List<String> findWords(char[][] board, String[] words) {
        maxRows = board.length-1;
        maxCols = board[0].length-1;
        head = new TrieNode();
        foundWords = new ArrayList<String>();

        for(String word : words) { //Form a trie using the words.
            buildTrie(word);
        }

        for(int i = 0; i <= maxRows; i++) { //For each beginning character, look for it in the trie.
            for(int j = 0; j <= maxCols; j++) {
                searchTrie(board, i, j, head);
            }
        }

        return foundWords;
    }

    //We recurse character by character, building the prefix 'word' with each character. Then we check if the prefix exists in the trie.
    public void searchTrie(char[][] board, int i, int j, TrieNode curr) {
        if(curr.endWord != null) { //A word has been found using the current recursion dfs.
            foundWords.add(curr.endWord);
            curr.endWord = null; //This is for duplicates. Marking it null means if we encounter the word again, this 'if' will fail and won't be added.
        }
        if(i < 0 || j < 0) { //Out of bounds.
            return;
        }
        if(i > maxRows || j > maxCols) { //Out of bounds.
            return;
        }

        char c = board[i][j];
        if(c == '#') { //Already checked this position.
            return;
        }
        if(curr.node[c-'a'] == null) { //Check if new prefix exists in the trie. New prefix is current prefix+c.
            return;
        }

        curr = curr.node[c-'a']; //Since prefix is found, take that and recursively check the next prefix.

        board[i][j] = '#'; //Mark it so that we don't repeat the position.
        searchTrie(board, i-1, j, curr); //Prev row.
        searchTrie(board, i+1, j, curr); //Next row.
        searchTrie(board, i, j-1, curr); //Prev col.
        searchTrie(board, i, j+1, curr); //Next col.
        board[i][j] = c; //Backtrack.
    }

    public void buildTrie(String word) {
        TrieNode curr = head;
        for(char c : word.toCharArray()) {
            int idx = c-'a';
            if(curr.node[idx] == null) {
                curr.node[idx] = new TrieNode();
            }
            curr = curr.node[idx];
        }
        curr.endWord = word;
    }

    class TrieNode {
        TrieNode[] node = new TrieNode[26];
        String endWord;
    }
}

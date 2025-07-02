package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {
    /*
    https://leetcode.com/problems/word-search-ii/
    A better solution is found in WordSearch2Alt.
     */
    TrieNode head;
    Set<String> foundWords;
    int maxRows, maxCols;
    public List<String> findWords(char[][] board, String[] words) {
        maxRows = board.length-1;
        maxCols = board[0].length-1;
        head = new TrieNode();
        foundWords = new HashSet<String>();

        for(String word : words) { //Form a trie using the words.
            buildTrie(word);
        }

        for(int i = 0; i <= maxRows; i++) { //For each beginning character, look for it in the trie.
            for(int j = 0; j <= maxCols; j++) {
                searchTrie(board, i, j, head, "");
            }
        }

        return new ArrayList<String>(foundWords);
    }

    //We recurse character by character, building the prefix 'word' with each character. Then we check if the prefix exists in the trie.
    public void searchTrie(char[][] board, int i, int j, TrieNode curr, String word) {
        if(word.length() > 10) { //Constraint is 10. We don't need to check past 10.
            return;
        }

        if(curr.endWord) { //A word has been found using the current recursion dfs.
            if(!foundWords.contains(word)) { //Add only if word not already added to set.
                foundWords.add(word);
            }
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

        String newWord = word+c; //The new prefix that we just confirmed exists.
        curr = curr.node[c-'a']; //Since prefix is found, take that and recursively check the next prefix.

        board[i][j] = '#'; //Mark it so that we don't repeat the position.
        searchTrie(board, i-1, j, curr, newWord); //Prev row.
        searchTrie(board, i+1, j, curr, newWord); //Next row.
        searchTrie(board, i, j-1, curr, newWord); //Prev col.
        searchTrie(board, i, j+1, curr, newWord); //Next col.
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
        curr.endWord = true;
    }

    class TrieNode {
        TrieNode[] node = new TrieNode[26];
        boolean endWord;
    }
}

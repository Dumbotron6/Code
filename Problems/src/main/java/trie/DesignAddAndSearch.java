package trie;

import java.util.HashMap;
import java.util.Map;

public class DesignAddAndSearch {

    /*
    https://leetcode.com/problems/design-add-and-search-words-data-structure/
     */
    class WordDictionary {

        Map<Character, WordDictionary> dict;
        boolean endWord;
        public WordDictionary() {
            dict = new HashMap<Character, WordDictionary>();
            endWord = false;
        }

        public void addWord(String word) {
            WordDictionary curr = this;
            for(char c : word.toCharArray()) {
                if(!curr.dict.containsKey(c)) {
                    curr.dict.put(c, new WordDictionary());
                }
                curr = curr.dict.get(c);
            }
            curr.endWord = true;
        }

        public boolean search(String word) {
            WordDictionary curr = this;
            for(int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                //If it is a dot, check recursively. Instead of O(N) complexity, we'd get O((26^2) * n) complexity as there are at most 2 dots.
                if(c == '.') {
                    for(Map.Entry<Character, WordDictionary> entry : curr.dict.entrySet()) {
                        if(entry.getValue().search(word.substring(i+1, word.length()))) {
                            return true;
                        }
                    }
                }
                if(!curr.dict.containsKey(c)) {
                    return false;
                }
                curr = curr.dict.get(c);
            }
            return curr.endWord;
        }
    }
}

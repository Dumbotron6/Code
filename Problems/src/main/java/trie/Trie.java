package trie;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/implement-trie-prefix-tree/
    or Implement Trie.
 */
class Trie {
    Map<Character, Trie> children;
    boolean endWord;
    public Trie() {
        this.children = new HashMap<Character, Trie>();
        this.endWord = false;
    }

    public void insert(String word) {
        Trie curr = this;
        for(char c : word.toCharArray()) {
            if(!curr.children.containsKey(c)) {
                curr.children.put(c, new Trie());
            }
            curr = curr.children.get(c);
        }
        curr.endWord = true;
    }

    public boolean search(String word) {
        Trie curr = this;
        for(char c : word.toCharArray()) {
            if(curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            }else {
                return false;
            }
        }
        return curr.endWord;
    }

    public boolean startsWith(String prefix) {
        Trie curr = this;
        for(char c : prefix.toCharArray()) {
            if(curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            }else {
                return false;
            }
        }
        return true;
    }
}

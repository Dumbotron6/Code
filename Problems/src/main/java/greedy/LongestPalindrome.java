package greedy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalindrome {
    /*
    https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/
     */

    //Instead of map, we and also use 2d array with [26][26].
    public int longestPalindrome(String[] words) {
        Map<String, Integer> wordCount = new HashMap<String, Integer>(); //Map to track string/stringReverse counts.

        int total = 0;
        //For each word, check if its reverse exist, if not add it as a new string.
        for(String word : words) {
            if(wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word)+1);
            }else {
                wordCount.put(word, 1);
            }
        }

        boolean lonePal = false;
        Set<String> keys = new HashSet<String>(wordCount.keySet()); //Due to concurrent modification if used directly.
        for(String word : keys) {
            String revWord = new StringBuilder(word).reverse().toString();
            if(word.charAt(0) != word.charAt(1)) {
                if(wordCount.containsKey(revWord)) {
                    total += Math.min(wordCount.get(word), wordCount.get(revWord))*4;
                    wordCount.remove(word); //So that word isn't double counted when loop reaches revWord.
                }
            }else {//For anything like "gg".
                int count = wordCount.get(word);
                int rem = count%2;
                count -= rem; //Why? We need to count even number. For example, "kkkk",
                total += count*2;
                if(rem == 1) { //Only 1 unique lone palindrome can be taken. "gg" and "dd" can't both be taken.
                    lonePal = true;
                }
            }

        }

        if(lonePal) {
            total += 2;
        }

        return total;
    }
}

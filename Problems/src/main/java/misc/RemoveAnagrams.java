package misc;

import java.util.ArrayList;
import java.util.List;

public class RemoveAnagrams {
    /*
    https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/
     */

    public List<String> removeAnagrams(String[] words) {
        int[] charCount1;
        int[] charCount2;

        List<String> finalList = new ArrayList<String>();

        int i = 0, j = 1;
        charCount1 = getCount(words[i]);

        while(j < words.length) {
            charCount2 = getCount(words[j]);
            if(!isAnagram(charCount1, charCount2)) {
                finalList.add(words[i]);
                charCount1 = charCount2;
                i = j;
            } //If anagram, keep i same and move j till non anagram is found.
            j++;
        }

        finalList.add(words[i]);

        return finalList;
    }

    public boolean isAnagram(int[] charCount1, int[] charCount2) {
        for(int k = 0; k < 26; k++) {
            if(charCount1[k] != charCount2[k]) {
                return false;
            }
        }

        return true;
    }

    public int[] getCount(String word) {
        int[] count = new int[26];

        for(char c : word.toCharArray()) {
            count[c-'a']++;
        }

        return count;
    }
}

package strings;

public class ValidAnagram {
    /*
    https://leetcode.com/problems/valid-anagram/
     */

    /*
    Every char we encounter in s, we increase frequency. For every char in s, we decrease frequency.
    If the two strings are anagrams, the end result would be 0 frequency for every char.
     */
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length())
            return false;

        int[] freq = new int[26];

        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)-'a']++;
            freq[t.charAt(i)-'a']--;
        }

        for(int i = 0; i < freq.length; i++) {
            if(freq[i] != 0)
                return false;
        }

        return true;
    }
}

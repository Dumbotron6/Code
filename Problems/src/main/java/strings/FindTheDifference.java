package strings;

public class FindTheDifference {
    /*
    https://leetcode.com/problems/find-the-difference/description/
     */

    /*
    For every char in s, increase. For every char in t, decrease.
    If both char counts are same, then it would ultimately be 0.
     */
    public char findTheDifference(String s, String t) {
        int[] count = new int[26];

        for(char c : s.toCharArray()) {
            count[c-'a']++;
        }

        for(char c : t.toCharArray()) {
            count[c-'a']--;
            if(count[c-'a'] < 0) {
                return c;
            }
        }

        return 'a';
    }
}

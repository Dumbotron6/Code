package slidingWindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeat {
    /*
    https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
     */

    /*
    As we find new characters, we keep adding to the set.
    When we find an existing character, we keep removing till we reach the position where the character was
        first encountered.
     */
    public int lengthOfLongestSubstring(String s) {
        int begin = 0;
        int end = 0;
        Set<Character> tracker = new HashSet<Character>();
        int longest = 0;
        int temp = 0;
        while(end < s.length()) {
            if(!tracker.contains(s.charAt(end))) {
                tracker.add(s.charAt(end));
            }else {
                while(s.charAt(begin) != s.charAt(end)) {
                    tracker.remove(s.charAt(begin));
                    begin++;
                }
                begin++;
            }
            end++;
            temp = end-begin;
            longest = Math.max(longest, temp);
        }
        return longest;
    }
}

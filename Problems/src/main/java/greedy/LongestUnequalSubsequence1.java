package greedy;

import java.util.ArrayList;
import java.util.List;

public class LongestUnequalSubsequence1 {
    /*
    https://leetcode.com/problems/longest-unequal-adjacent-groups-subsequence-i/
     */

    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<String>();
        result.add(words[0]);
        int prev = groups[0];
        for(int i = 0; i < words.length; i++) {
            if(prev != groups[i]) {
                prev = groups[i];
                result.add(words[i]);
            }
        }

        return result;
    }
}

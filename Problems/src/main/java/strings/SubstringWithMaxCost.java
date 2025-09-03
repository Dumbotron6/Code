package strings;

import java.util.Arrays;

public class SubstringWithMaxCost {
    /*
    https://leetcode.com/problems/find-the-substring-with-maximum-cost/description/
     */

    //Same as MaximumSubarray
    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] costs = new int[26];
        Arrays.fill(costs, 1001);

        for(int i = 0; i < chars.length(); i++) {
            costs[chars.charAt(i)-'a'] = vals[i];
        }

        for(int i = 0; i < 26; i++) {
            if(costs[i] == 1001) {
                costs[i] = i+1;
            }
        }

        int score = 0, maxScore = score;

        for(int i = 0; i < s.length(); i++) {
            score += costs[s.charAt(i)-'a'];
            maxScore = Math.max(maxScore, score);
            if(score < 0) {
                score = 0;
            }
        }

        return maxScore;
    }
}

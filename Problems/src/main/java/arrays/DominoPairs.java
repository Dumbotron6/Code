package arrays;

import java.util.HashMap;
import java.util.Map;

public class DominoPairs {
    /*
    https://leetcode.com/problems/number-of-equivalent-domino-pairs/
     */

    /*
    Two same dominoes make one pair. Three same dominoes make three pairs. Four same dominoes make six pairs.
    */
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<String, Integer> tracker = new HashMap<String, Integer>();
        int totalPairs = 0;

        for(int[] domino : dominoes) {
            String key = "";
            if(domino[0] <= domino[1]) {
                key = ""+domino[0]+"_"+domino[1];
            }else {
                key = ""+domino[1]+"_"+domino[0];
            }

            if(tracker.containsKey(key)) {
                int val = tracker.get(key);
                totalPairs += val;
                tracker.put(key, val+1);
            }else {
                tracker.put(key, 1);
            }
        }

        return totalPairs;
    }

    /*
    NOTE: Since the constraints are the domino[i][j] will lie between 1 and 9, instead of hashmap,
        we can use an array of length 100. Instead of the key above, we can use
        domino[larger]*10 + domino[smaller] or vice versa as they key.
     */
}

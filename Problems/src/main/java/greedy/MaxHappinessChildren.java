package greedy;

import java.util.Arrays;

public class MaxHappinessChildren {
    /*
    https://leetcode.com/problems/maximize-happiness-of-selected-children/description/
     */

    //Select top k max happiness or till we hit a negative.
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int len = happiness.length;

        long maxHappiness = 0;

        for(int i = 0; i < k; i++) {
            int val = happiness[len-i-1] - i;
            if(val <= 0) {
                return maxHappiness;
            }
            maxHappiness += val;
        }

        return maxHappiness;
    }
}

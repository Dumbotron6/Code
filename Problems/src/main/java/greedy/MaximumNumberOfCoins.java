package greedy;

import java.util.Arrays;

public class MaximumNumberOfCoins {
    /*
    https://leetcode.com/problems/maximum-number-of-coins-you-can-get/
     */

    /*
    We sort the piles, leave smallest for bob each time, take the second largest. So during each pick,
        the pile will have [largest, second largest, smallest].
    We can use right-=2 (for alice and us) and left+=1 (for bob). Or as below, cover only till maxDist.
    */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int maxTotal = 0;
        int maxDist = piles.length/3;

        for(int i = piles.length-2; i >= maxDist; i -= 2) {
            maxTotal += piles[i];
        }

        return maxTotal;
    }
}

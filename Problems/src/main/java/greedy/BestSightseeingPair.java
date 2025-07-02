package greedy;

public class BestSightseeingPair {
    /*
    https://leetcode.com/problems/best-sightseeing-pair/
     */

    /*
    We need to keep track of the max element and add it to the current element to find the best pair.
    Now why max = values[0]-1 and max--? It's because the best pair should be max of values[i]+values[j]+(i-j).
    As we move right, (i-j) would keep increasing, ie, 0-1,0-2,0-3 etc. So effectively, we are decreasing the max value.
    So instead of keeping track of max index, we can simply keep reducing max by 1. Because as seen above, i-j keeps
        increasing(in the negative) by 1.
     */
    public int maxScoreSightseeingPair(int[] values) {
        int best = 0;
        int max = values[0]-1;
        for(int i = 1; i < values.length; i++) {
            best = Math.max(best, max+values[i]);
            max = Math.max(max, values[i]);
            max--;
        }
        return best;
    }
}

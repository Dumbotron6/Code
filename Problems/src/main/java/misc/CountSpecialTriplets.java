package misc;

import java.util.HashMap;
import java.util.Map;

public class CountSpecialTriplets {
    /*
    https://leetcode.com/problems/count-special-triplets/
     */

    //Counting problem. Don't mess up int to long type conversion.
    public int specialTriplets(int[] nums) {
        Map<Integer, Integer> leftCount = new HashMap<Integer, Integer>();
        Map<Integer, Integer> rightCount = new HashMap<Integer, Integer>();

        int mod = (int)1e9+7;

        for(int num : nums) {
            rightCount.put(num, rightCount.getOrDefault(num, 0)+1);
        }

        int triplets = 0;

        for(int num : nums) {
            int doubVal = num*2;
            int cnt = rightCount.get(num);
            if(cnt > 1) {
                rightCount.put(num, cnt-1);
            }else {
                rightCount.remove(num);
            }
            if(leftCount.containsKey(doubVal) && rightCount.containsKey(doubVal)) {
                triplets = (int)(((long)triplets + ((long)leftCount.get(doubVal) * (long)rightCount.get(doubVal)))%mod);
            }
            leftCount.put(num, leftCount.getOrDefault(num, 0)+1);
        }

        return triplets;
    }
}

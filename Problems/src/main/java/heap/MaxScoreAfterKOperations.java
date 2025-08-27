package heap;

import java.util.PriorityQueue;

public class MaxScoreAfterKOperations {
    /*
    https://leetcode.com/problems/maximal-score-after-applying-k-operations/
     */

    public long maxKelements(int[] nums, int k) {
        long maxVal = 0;

        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>((a, b) -> b-a);
        for(int num : nums) {
            maxQueue.offer(num);
        }

        for(int i = 0; i < k; i++) {
            int val = maxQueue.poll();
            maxVal += val;
            if(val%3 == 0) {
                maxQueue.offer(val/3);
            }else {
                maxQueue.offer(val/3 + 1);
            }
        }

        return maxVal;
    }
}

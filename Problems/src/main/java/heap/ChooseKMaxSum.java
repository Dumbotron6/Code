package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class ChooseKMaxSum {
    /*
    https://leetcode.com/problems/choose-k-elements-with-maximum-sum/
     */

    /*
    For nums1 = [4,2,1,5,3], nums2 = [10,20,30,40,50], k = 2,
        for index 0, take everything less than 4 which are 2,3,1 its corresponding nums2 which are 20,30,50.
    Then take k max and sum them up. So for index 0, the answer is 80.
    */
    public long[] findMaxSum(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(); //To stoke k max nums2.
        int len = nums1.length;

        long[] result = new long[len];
        int[][] pairs = new int[len][3];

        for(int i = 0; i < len; i++) {
            pairs[i][0] = nums1[i];
            pairs[i][1] = nums2[i];
            pairs[i][2] = i;
        }

        Arrays.sort(pairs, (a, b) -> {
            if(a[0] == b[0]) { //If nums1 match, sort by nums2.
                return a[1]-b[1];
            }
            return a[0]-b[0]; //Sort by nums1.
        });

        long sum = 0;
        for(int i = 1; i < len; i++) {
            pq.offer(pairs[i-1][1]);
            sum += pairs[i-1][1];

            if(pq.size() > k) { //Keep max size of pq to be k ie, k maximum nums2.
                sum -= pq.poll();
            }

            if(pairs[i][0] > pairs[i-1][0]) {
                result[pairs[i][2]] = sum;
            }else { //For nums1 cases like [1,2,2]. Here, for 2,2 the sum should be same.
                result[pairs[i][2]] = result[pairs[i-1][2]];
            }
        }

        return result;
    }
}

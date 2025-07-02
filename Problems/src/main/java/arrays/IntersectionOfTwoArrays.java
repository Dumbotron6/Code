package arrays;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfTwoArrays {
    /*
    https://leetcode.com/problems/intersection-of-two-arrays/
     */

    public int[] intersection(int[] nums1, int[] nums2) {
        int[] numCount = new int[1001];

        for(int i = 0; i < nums1.length; i++) {
            numCount[nums1[i]]++;
        }

        List<Integer> resultList = new ArrayList<Integer>();
        for(int i = 0; i < nums2.length; i++) {
            if(numCount[nums2[i]] > 0) {
                numCount[nums2[i]] = 0;
                resultList.add(nums2[i]);
            }
        }

        int[] result = new int[resultList.size()];
        for(int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}

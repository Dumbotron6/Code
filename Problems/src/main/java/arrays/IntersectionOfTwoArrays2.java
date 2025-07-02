package arrays;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfTwoArrays2 {
    /*
    https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
     */

    public int[] intersect(int[] nums1, int[] nums2) {
        int[] count = new int[1001];

        for(int i = 0; i < nums1.length; i++) {
            count[nums1[i]]++;
        }
        List<Integer> inter = new ArrayList<Integer>();
        for(int i = 0; i < nums2.length; i++) {
            if(count[nums2[i]] > 0) {
                inter.add(nums2[i]);
                count[nums2[i]]--;
            }
        }

        int[] result = new int[inter.size()];

        for(int i = 0; i < result.length; i++) {
            result[i] = inter.get(i);
        }

        return result;
    }
}

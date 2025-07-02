package arrays;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    /*
    https://leetcode.com/problems/summary-ranges/description/
     */

    //Uses two pointers to keep track of range.
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<String>();
        if(nums.length == 0) {
            return result;
        }
        int begin = 0;
        int end = 0;

        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i-1]+1) {
                end = i;
            }else {
                if(begin != end) {
                    result.add(""+nums[begin]+"->"+nums[end]);
                }else {
                    result.add(""+nums[begin]);
                }
                begin = i;
                end = i;
            }
        }
        if(begin != end) {
            result.add(""+nums[begin]+"->"+nums[end]);
        }else {
            result.add(""+nums[begin]);
        }

        return result;
    }
}

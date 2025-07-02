package arrays;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement2 {
    /*
    https://leetcode.com/problems/majority-element-ii/
    Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     */

    /*
    For elements that appear more than n/3 times, there can only be a max of 2 elements.
    So we find the two top most occurring elements as below.
    Applying the same logic as 'MajorityElement', we increase count when encountering either element
        and decrease count when it's not one of the either elements.
     */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> greater = new ArrayList<Integer>();
        int count1 = 0;
        int count2 = 0;
        int val1 = 0;
        int val2 = 0;

        for(int i = 0; i < nums.length; i++) {
            if(count1 == 0 && nums[i] != val2) {
                val1 = nums[i];
                count1++;
            }else if(count2 == 0 && nums[i] != val1) {
                val2 = nums[i];
                count2++;
            } else {
                if(nums[i] == val1)
                    count1++;
                else if(nums[i] == val2)
                    count2++;
                else {
                    count1--; count2--;
                }
            }

        }

        /*
        Now that we have found the two top most occurring elements, we find their count.
         */
        count1 = count2 = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == val1)
                count1++;
            else if(nums[i] == val2)
                count2++;
        }

        //We only include elements in the final list if their length is > n/3.
        if(count1 >= nums.length/3 + 1)
            greater.add(val1);

        if(count2 >= nums.length/3 + 1)
            greater.add(val2);


        return greater;
    }
}

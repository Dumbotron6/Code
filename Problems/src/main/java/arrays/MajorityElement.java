package arrays;

public class MajorityElement {
    /*
    https://leetcode.com/problems/majority-element/
     */


    /*
    Majority element occurs >n/2 times.
    If we have a single count variable, going by below logic,
    If we have sat [1,1,1,3,4], The count would go to 3 then 1.
    That is to say, the majority element will always have a positive count as it is greater than half the
        length of the array.
    So even if all other elements make up (n/2 - 1) elements, we'd have 1 count of the majority element as above.
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int element = nums[0];

        for(int i = 0; i < nums.length; i++) {
            if(count == 0)
                element = nums[i];

            if(nums[i] == element)
                count++;
            else
                count--;
        }

        return element;
    }
}

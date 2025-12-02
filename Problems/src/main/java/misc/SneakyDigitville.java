package misc;

public class SneakyDigitville {
    /*
    https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/
     */

    public int[] getSneakyNumbers(int[] nums) {
        int len = nums.length;
        int[] result = new int[2];
        int j = 0;

        for(int i = 0; i < len; i++) {
            int val = Math.abs(nums[i]);
            if(val == len) {
                val = 0;
            }

            if(nums[val] < 0 || nums[val] == len) {
                result[j++] = val;
            }else if(nums[val] == 0){
                nums[val] = len;
            }else {
                nums[val] = -nums[val];
            }

            if(j == 2) {
                break;
            }
        }

        return result;
    }
}

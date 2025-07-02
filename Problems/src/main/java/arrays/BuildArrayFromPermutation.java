package arrays;

public class BuildArrayFromPermutation {
    /*
    https://leetcode.com/problems/build-array-from-permutation
     */

    //There is a solution with O(1) space. Look that up.
    public int[] buildArray(int[] nums) {
        int[] finalArray = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            finalArray[i] = nums[nums[i]];
        }

        return finalArray;
    }
}

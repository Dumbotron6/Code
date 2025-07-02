package arrays;

public class ArrayElementsBySign {
    /*
    https://leetcode.com/problems/rearrange-array-elements-by-sign/description/

    When given an array like [3,1,-2,-5,2,-4], positives should be followed by negatives alternatively [3,-2,1,-5,2,-4].
    Order should be preserved.
     */

    public int[] rearrangeArray(int[] nums) {

        /*
        We initialize the return array and keep track of alternate positions(even and odd) using i and j.
        pos iterates one by one. When we encounter positive, place it at i and move i to next even position (0 to 2).
        When we encounter negative, place it at j and move j to next odd position (1 to 3).
         */
        int[] finalArray = new int[nums.length];

        int i = 0; int j = 1; int pos = 0;

        while(pos < finalArray.length) {
            if(nums[pos] > 0) {
                finalArray[i] = nums[pos++];
                i += 2;
            }else {
                finalArray[j] = nums[pos++];
                j += 2;
            }
        }

        return finalArray;
    }
}

package arrays;

public class NextGreaterPermutation {
    /*
    https://leetcode.com/problems/next-permutation/

    For an array say [1,2,3], the next permutations are [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]
     */
    public void nextPermutation(int[] nums) {

        /*
        Observe that in the permutations list, the next one is greater than the last. Like 132 is greater than 123.
        Keeping that in mind, we need to look for the next greatest number.
        To do that, first we need to identify which element in the array we need to change from.
        */

        int breakpoint = -1;

        /*
        If we take an array [1,2,5,3,0], looking from the right, we have 530 and then 12. There is a rise and fall curve.
        Taking the element where the fall happens is the place we need to change from.
        The reason being, for anything greater than 12 in the permutation, it has to be greater than 12.
        ie. 2 has to be replaced with its smallest greater element.
         */
        for(int i = nums.length-1; i > 0; i--) {
            if(nums[i] > nums[i-1]) {
                breakpoint = i-1;
                break;
            }
        }

        /*
        From the right, we look for the smallest element greater than our breakpoint.
        Since there is already a rise from the right, we can safely iterate from the right to find it.
         */
        if(breakpoint != -1) {
            for(int i = nums.length-1; i > breakpoint; i--) {
                if(nums[i] > nums[breakpoint]) {
                    swap(nums, breakpoint, i);
                    break;
                }
            }
            /*
            Reversing the array from the breakpoint after swapping would give us an array that is rising from breakpoint.
            For example, reversing from breakpoint in [1,3,5,2,0] will give us [1,3,0,2,5] which gives us 025.
            Now 025 is smaller than any other combination with 5,2,0.
            This ensures that we have the smallest greater permutation available.
             */
            reverse(nums, breakpoint+1, nums.length-1);
        }else {
            /*
            We can directly reverse if the array is already at its greatest.
            [5,4,3,2,1] would give us [1,2,3,4,5] which is next in line in the permutation.
             */
            reverse(nums, 0, nums.length-1);
        }

    }

    public void swap(int[] nums, int bp, int gr) {
        int temp = nums[bp];
        nums[bp] = nums[gr];
        nums[gr] = temp;
    }

    public void reverse(int[] nums, int i, int j){
        while(i < j) {
            swap(nums, i++, j--);
        }
    }
}

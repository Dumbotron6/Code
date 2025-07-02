package arrays;

public class MaximumProductSubarray {
    /*
    https://leetcode.com/problems/maximum-product-subarray/
     */

    /*
    Taking an array of [1,2,3,4,-5,6,7]
    If there are even number of -ves then the ultimate result would be positive.
    If there are odd number of -ve, the greatest product can be on the left of the -ve or the right.
    In the above case, it is [1,2,3,4] or [6,7].
    If there are multiple -ves, we are left with multiple possibilities.
    [1,2,3,-4,5,6,-6,7,-3,9,8] gives us possibilities of [1,2,3,-4,5,6,-6,7][9,8]
        and [1,2,3][5,-6,7,-3,9,8]. As the number of -ves increase, so do the possibilities.
    If we get the product from the left, we get possibility 1 in the above array and if we get the product
        from the right, we get possibility 2. We can get the max of the two.
    So any combination missed by the left will be covered by the right, even for larger number of negatives.
    Because we ultimately need to find the correct -ve to not multiply to get the max.

    Ex. For [1,2,3,4,-5,6,7], from left we get [1,2,6,24,-120,-720,-5040].
        From right we get [-5040,-5040,-2520,-840,-210,42,7]. We get the max which is 42.

    Ex. [1,2,-3,-4,-5,6,7], from left we get [1,2,-6,24,-120,-720,-5040].
        From right we get [-5040,-5040,-2520,840,-210,42,7]. We get the max which is 840.

    The comparison with Integer.MIN_VALUE is because for [10,10,10,10,-10,10,10,10,10,10]
        would exceed integer value during calculation.
    But the problem statement guarantees that the max product would be within int value.
    This means it will not exceed MAX_VALUE. The only reason we keep multiplying when we hit -ve is to hopefully
        hit another -ve down the line to make the product positive.
    So at any point if it exceeds MIN_VALUE, we know that further calculation is futile. So we reset the product.
     */
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int product = 1;

        for(int num : nums) {
            if((long)product*(long)num < Integer.MIN_VALUE )
                product = 1;
            product *= num;
            maxProduct = Math.max(product, maxProduct);
            if(num == 0) product = 1;
        }

        product = 1;
        for(int i = nums.length-1; i >= 0; i--) {
            if((long)product*(long)nums[i] < Integer.MIN_VALUE )
                product = 1;
            product *= nums[i];
            maxProduct = Math.max(product, maxProduct);
            if(nums[i] == 0) product = 1;
        }

        return maxProduct;
    }
}

package arrays;

public class ProductExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        /*
        At any point, prefix will have product up to it's last element. ie, at i, product will be till i-1.
        Same for suffix but in reverse. So when we do a prefix*suffix, then we get product of
            (everything to left of i) * (everything to right of i).
        */
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] prods = new int[n];
        /*
        Why do the below? Because there's nothing to left of 0 and nothing to right of n-1.
        So we set default 1.
        */
        prefix[0] = 1;
        suffix[n-1] = 1;

        for(int i = 1; i < n; i++) {
            prefix[i] = nums[i-1]*prefix[i-1];
            suffix[n-i-1] = suffix[n-i]*nums[n-i];
        }

        for(int i = 0; i < n; i++) {
            prods[i] = prefix[i]*suffix[i];
        }

        return prods;
    }

    //Better
    public int[] productExceptSelfBetter(int[] nums) {
        int n = nums.length;
        int preProd[] = new int[n];
        int postProd[] = new int[n];
        preProd[0] = 1;
        postProd[n-1] = 1;

        for(int i = 1; i < n; i++) {
            preProd[i] = preProd[i-1]*nums[i-1];
            postProd[n-i-1] = postProd[n-i]*nums[n-i];
        }

        for(int i = 0; i < n; i++) {
            preProd[i] *= postProd[i];
        }

        return preProd;
    }

    //Optimal. Uses O(1) space.
    public int[] productExceptSelfOptimal(int[] nums) {
        int n = nums.length;
        int result[] = new int[n];
        result[0] = 1;

        for(int i = 1; i < n; i++) {
            result[i] = result[i-1]*nums[i-1];
        }

        int right = 1; //We eliminated using post product prefix and calculate it on the go.
        for(int i = n-1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }

        return result;
    }
}

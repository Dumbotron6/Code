package misc;

public class DivisiblebyThree {
    /*
    https://leetcode.com/problems/find-minimum-operations-to-make-all-elements-divisible-by-three/
     */

    public int minimumOperations(int[] nums) {
        int total = 0;

        for(int num : nums) {
            if(num%3 != 0) {
                total++;
            }
        }

        return total;
    }
}

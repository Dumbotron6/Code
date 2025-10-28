package arrays;

public class MakeArrayZero {
    /*
    https://leetcode.com/problems/make-array-elements-equal-to-zero/
     */

    public int countValidSelections(int[] nums) {
        int valid = 0;

        int total = 0;
        for(int num : nums) {
            total += num;
        }

        int leftTotal = 0;
        for(int num : nums) {
            total -= num;
            leftTotal += num;
            if(num == 0) {
                if(leftTotal == total) { //Moving left and moving right are valid.
                    valid += 2;
                }else if(leftTotal == total+1) { //Moving left is valid.
                    valid++;
                }else if(total == leftTotal+1) { //Moving right is valid.
                    valid++;
                }
            }
        }

        return valid;
    }
}

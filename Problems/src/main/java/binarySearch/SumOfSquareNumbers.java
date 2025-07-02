package binarySearch;

public class SumOfSquareNumbers {
    /*
    https://leetcode.com/problems/sum-of-square-numbers/description/
     */

    public boolean judgeSquareSum(int c) {
        long right = (long)Math.sqrt(c);
        long left = 0;
        while(left <= right) {
            long sqrSum = left*left + right*right;
            if(sqrSum > c) {
                right--;
            }else if(sqrSum < c) {
                left++;
            }else {
                return true;
            }
        }
        return false;
    }
}

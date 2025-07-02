package binarySearch;

public class ValidPerfectSquare {
    /*
    https://leetcode.com/problems/valid-perfect-square/description/
     */

    public boolean isPerfectSquare(int num) {
        if(num == 1) {
            return true;
        }

        long left = 2;
        long right = num/2;

        while(left <= right) {
            long mid = left+(right-left)/2;
            long prod = mid*mid;
            if(prod == num) {
                return true;
            }else if(prod > num) {
                right = mid-1;
            }else {
                left = mid+1;
            }
        }

        return false;
    }
}

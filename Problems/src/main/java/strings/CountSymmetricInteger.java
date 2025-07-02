package strings;

public class CountSymmetricInteger {
    /*
    https://leetcode.com/problems/count-symmetric-integers/
     */

    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        for(int i = low; i <= high; i++) {
            if(isSymmetric(String.valueOf(i))) {
                count++;
            }
        }
        return count;
    }

    public boolean isSymmetric(String num) {
        int len = num.length();
        if(len%2 != 0) {
            return false;
        }

        int sum1 = 0, sum2 = 0;
        int i = len-1;
        while(i >= len/2) {
            sum1 += num.charAt(i)-'0';
            i--;
        }
        while(i >= 0) {
            sum2 += num.charAt(i)-'0';
            i--;
        }

        return sum1 == sum2;
    }
}

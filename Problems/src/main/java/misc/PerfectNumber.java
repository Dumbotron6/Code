package misc;

public class PerfectNumber {
    /*
    https://leetcode.com/problems/perfect-number/description/
     */

    public boolean checkPerfectNumber(int num) {
        if(num == 1) {
            return false;
        }

        int sum = 1;
        int max = (int)Math.sqrt(num);

        for(int i = 2; i <= max; i++) {
            if(num%i == 0) {
                sum += i + num/i;
            }
        }

        return sum == num;
    }
}

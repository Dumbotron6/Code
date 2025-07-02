package misc;

public class HappyNumber {
    /*
    https://leetcode.com/problems/happy-number/description/
     */

    /*
    The key here is to detect an endless loop, ie, a cycle. This is a twist on using fast and slow pointer.
    Now whatever digit we take, due to fast being ahead, it will always circle back and encounter fast if there is a loop.
    */
    public boolean isHappy(int n) {
        int slow = n;
        int fast = squareSum(n);
        while(slow != fast) {
            slow = squareSum(slow);
            fast = squareSum(squareSum(fast));
        }

        return slow == 1;
    }

    public int squareSum(int num) {
        int sum = 0;
        while(num > 0) {
            int val = num%10;
            sum += (val*val);
            num /= 10;
        }

        return sum;
    }
}

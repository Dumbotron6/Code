package recursion;

public class Pow {
    /*
    https://leetcode.com/problems/powx-n/description/

    Solution seems simple(first solution attempted in myPowInitial method).
    Problem with that approach is, if we give INT_MAX or INT_MIN, then we run into stack overflow.
    Stack overflow is because of the sheer number of recursive calls rather than x or n exceeding max value.
     */

    /*
    What this approach does is, for 2^8, it works on the principle of dividing its values.
    ie. 2^8 is same as 4^4 which is same as 16^2.
    Doing it this way reduces number of recursive calls.
     */
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;
        if(n > 0) {
            return powerVal(x, n);
        }else {
            return 1/powerVal(x, n);
        }

    }

    public double powerVal(double x, int n) {
        if(n == 1 || n == -1)
            return x;
        double val = powerVal(x*x, n/2);
        /*
        Example, 2^2 should give 4. 2^3 should give 8.
        n/2 gives a value which is passed into recursion.
        So for both 2^2 and 2^3, we'd get val as 4.
        Below part takes care of odd powers like 2^3.
         */
        if(n % 2 == 0) {
            return val;
        }else {
            return x * val;
        }
    }

    /*
    This is a bruteforce approach. Here, we run into overflow as stated above.
    Instead of --n in powerValInitial, we can simply do the below
        if (n < 0) {
            n *= -1;
            x = 1 / x;
        }
     But the problem here is, if n is INT_MIN, we'd still run into overflow when converting to positive.
     So we'd need to store n as long to avoid that overflow.
     */
    public double myPowInitial(double x, int n) {
        if(n == 0)
            return 1;
        return powerValInitial(x, x, n);
    }

    public double powerValInitial(double x, double val, int n) {
        if(n > 1) {
            return powerValInitial(x, val*x, --n);
        }else if(n < -1) {
            return powerValInitial(x, val*x, ++n);
        }else if(n > 0)
            return val;
        else
            return 1/val;
    }
}

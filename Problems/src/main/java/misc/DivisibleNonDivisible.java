package misc;

public class DivisibleNonDivisible {
    /*
    https://leetcode.com/problems/divisible-and-non-divisible-sums-difference/
     */

    public int differenceOfSums(int n, int m) {
        int nDivSum = 0;

        for(int i = m; i <= n; i+=m) {
            nDivSum += i;
        }

        int totalSum = n*(n+1)/2;

        return totalSum-nDivSum-nDivSum;
    }
}

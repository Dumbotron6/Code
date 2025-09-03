package misc;

public class CountColoredCells {
    /*
    https://leetcode.com/problems/count-total-number-of-colored-cells/description/
     */

    public long coloredCells(int n) {
        long total = 0;
        for(int i = 1; i < n; i++) {
            total += i*4;
        }
        return total+1;
    }

    public long coloredCellsAlt(int n) {
        /*
        The count increases with each n - 4,12,16 etc. Which is 4*(1+2+3+4 etc) which is what we did in the previous submitted solution by looping.
        This can also be represented by 4*(n*(n+1)/2). So why (n-1)*(n)/2. With 1, the actual solution would give us
            4, with 2, it will give us 12 etc. When in reality, it needs 0 for 1, 4 for 2, 12 for 3 etc. So we altered the formula a bit.
        */
        long total = (long)(n-1)*(long)(n)/2;
        total *= 4;
        return total+1;
    }
}

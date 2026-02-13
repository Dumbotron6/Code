package misc;

public class WaysDivideLongCorridor {
    /*
    https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/
     */

    public int numberOfWays(String corridor) {
        int mod = (int)1e9+7;
        int prev = 0;
        int count = 0;
        int total = 1;

        /*
        Whenever we encounter an odd seat, we can place a divider at anypoint between then to the previous pair.
        Which is why we do i-prev. Why multiply by total? Consider we encountered three pairs of twos with flowers in between each pair.
        If dividers can be placed in 2 places between pairs A and B, and in 3 places between B and C, then we have six possible combinations.
        */
        for(int i = 0; i < corridor.length(); i++) {
            if(corridor.charAt(i) == 'S') {
                count++;
                if(count%2 == 1 && prev != 0) {
                    total = (int)(((long)total * (i-prev))%mod);
                }else { //Two seats encountered, hold the end of that pair in 'prev'.
                    prev = i;
                }
            }
        }

        if(count%2 == 1 || count == 0) { //Odd number of seats can't be split.
            return 0;
        }

        return total;
    }
}

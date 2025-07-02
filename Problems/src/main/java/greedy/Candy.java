package greedy;

public class Candy {
    /*
    https://leetcode.com/problems/candy/
     */

    /*
    If the ratings are [6,7,1,2,1,3,4,5,6,3,2,1], the candy needs to be [1,2,1,2,1,2,3,4,3,2,1].
    From left to right, if we only check if each student rating is greater than the previous,
        the values would be [1,2,1,2,1,2,3,4,1,1,1].
    From right to left, if we only check if each student rating is greater than the next,
        the values would be [1,2,1,2,1,1,1,4,3,2,1].
    Now if we take the two and pick the max, we'd end up with the ideal candy distribution.
    Ideally, w'd use two arrays to keep store the left and the right candies.
    But using currentMax, we're able to determine the max value as we iterate from the right.
     */

    public int candyBetter(int[] ratings) {
        int totalCandy = 0;

        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i-1])
                candies[i] = candies[i-1]+1;
            else
                candies[i] = 1;
        }

        int currentMax = candies[ratings.length-1];

        for(int i = ratings.length-2; i >= 0; i--) {
            if(ratings[i] > ratings[i+1])
                currentMax++;
            else
                currentMax = 1;
            candies[i] = Math.max(currentMax, candies[i]);
        }

        for(int i = 0; i < ratings.length; i++)
            totalCandy += candies[i];

        return totalCandy;
    }

    /*
    In this approach, we consider the slope approach. ie, we track when the array rises and falls.
     */
    public int candyOptimal(int[] ratings) {
        int totalCandy = 1;
        int low = 1;
        int high = 1;
        int i = 1;

        while(i < ratings.length) {
            //If it is equal to previous rating, simply add 1.
            if(ratings[i] == ratings[i-1]) {
                totalCandy++;
                low = high = 1;
                i++;
                continue;
            }
            //If it is rising, increase the high count which is the number of candies per element.
            while(i < ratings.length && ratings[i] > ratings[i-1]) {
                high++;
                totalCandy += high;
                i++;
            }
            /*
            If it is falling, increase the low count which is the number of candies per element.
            Observing the above and below while loops show us that incrementing high/low is in different places.
            This is because we operate under the assumption that the lowest element(index 0 and any subsequent lows)
                would have 1 candy already given.
            Any rise would only add to this. This is for upward slope.
            For downward slope on [6,7,8,7,6,5,4,3,2,1], we go 1,2,3 then we go 1,2,3,4,... So at 7 after 8, we add 1.
            Hence totalCandy+=low.
             */
            while(i < ratings.length && ratings[i] < ratings[i-1]) {
                totalCandy += low;
                low++;
                i++;
            }
            /*
            The reason for doing this is, consider an array [6,7,8,7,6,5,4,3,2,1].
            Now rising would give us [1,2,3] candies for the first three.
            But falling from 8 would mean we'd need (from 8), [8,7,6,5,4,3,2,1]
            But what we'd actually have is (from 8) [3,1,2,3,4,5,6,7].
            It's fine for use to have 1 to 7 in this order as we don't really need to return the candy distribution,
                just the count.
            But the peak element needs to have 8. Which we can get by adding existing 3(which is already added),
                and (low-high) which is 5.
             */
            if(low >= high) {
                totalCandy += (low - high);
            }
            low = high = 1;
        }
        return totalCandy;
        /*
        NOTE: If we actually track the candy distribution in this method, it is [1,2,3(+5),1,2,3,4,5,6,7]
        The actual distribution should be [1,2,3,8,7,6,5,4,3,2,1].
        However, our goal is only the count, which is the same.
         */
    }
}

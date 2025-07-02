package binarySearch;

public class MaximumCandiesKChildren {
    /*
    https://leetcode.com/problems/maximum-candies-allocated-to-k-children/
     */

    public int maximumCandies(int[] candies, long k) {
        //Solution is almost the same as Koko eating bananas problem
        int maxCandies = 0;
        int minCandies = 1;
        for(int candy : candies) {
            maxCandies = Math.max(candy, maxCandies);
        }

        while(minCandies <= maxCandies) {
            int mid = minCandies + (maxCandies-minCandies)/2;
            if(hasSufficientCandies(candies, k, mid)) {
                minCandies = mid+1;
            }else {
                maxCandies = mid-1;
            }
        }
        return minCandies-1;
    }

    public boolean hasSufficientCandies(int[] candies, long k, int limit) {
        long children = 0;
        for(int candy : candies) {
            children += candy/limit;
            if(children >= k) {
                return true;
            }
        }
        return false;
    }
}

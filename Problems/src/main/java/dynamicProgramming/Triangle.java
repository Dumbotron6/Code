package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
    /*
    https://leetcode.com/problems/triangle/
     */

    /*
    We work from the bottom to calculate minimum possible value at each index at each row.
    For example, in [[2],[3,4],[6,5,7],[4,1,8,3]], the first one will always be [4,1,8,3].
    The second one will be [7,7,10] which we got by adding [6,5,7] to minimum values of [4,1,8,3] at adjacent indexes.
    By picking minimum values to add to each index, as we go up, we will ultimately end up with what needs to be the
        minimum value to be added to the last element which is [2].
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> minValue = new ArrayList<List<Integer>>();
        int n = triangle.size()-1;
        minValue.add(triangle.get(n)); //Nothing to add to the bottom of the triangle so we put it in minValue as is.

        for(int i = n-1; i >= 0; i--) {
            List<Integer> prevArray = minValue.get(n-1-i);
            List<Integer> currArray = triangle.get(i);
            List<Integer> result = new ArrayList<Integer>();

            for(int j = 0; j < currArray.size(); j++) {
                result.add(currArray.get(j)+Math.min(prevArray.get(j), prevArray.get(j+1)));
            }
            minValue.add(result);
        }

        return minValue.get(n).get(0);
    }

    /*
    NOTE: We technically don't need a minValue array to store the resultant values but that would mean changing the
        existing values of triangle.
    */

    /*
    Building on previous solution, we eliminate extra space and overwrite existing values in dp[] for our calculation.
    NOTE: In a triangle array, the number of rows will be the same as the number of elements at the bottom.
        For example, a triangle of height 4 will have 4 elements at the bottom.
    */
    public int minimumTotalOptimal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];

        for(int i = n-1; i >= 0; i--) { //Fill the last row with existing values as there is nothing to add.
            dp[i] = triangle.get(n-1).get(i);
        }

        for(int i = n-2; i >= 0; i--) {//We move up and pick minimum value for each index at each row.
            for(int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }

        return dp[0];
    }

    /*
    NOTE: We can safely reuse dp[]. At the last row, [0,1,2,3] will be present. As we move up, the index we use keeps reducing by one.
        At one up, only indexes [0,1,2] will be overwritten. [3] will never be touched again.
    */
}

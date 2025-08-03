package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    /*
    https://leetcode.com/problems/pascals-triangle/
     */

    /*
    At each edge, we add 1. If not, we check previous row, add it's previous and that index.
        ie, at index 1 in current row, we add previous row index 0 and index 1. For 2, we add 1 and 2.
    */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();

        for(int i=0; i<numRows; i++) {
            finalList.add(new ArrayList<Integer>());
            for(int j=0; j<=i ; j++) {
                if(j==0 || j==i)
                    finalList.get(i).add(1);
                else {
                    finalList.get(i).add(finalList.get(i-1).get(j-1)+finalList.get(i-1).get(j));
                }
            }
        }
        return finalList;
    }
}

package greedy;

import java.util.Arrays;

public class MinimumCostCutCake2 {
    /*
    https://leetcode.com/problems/minimum-cost-for-cutting-cake-ii/description/
     */

    /*
    The highest cost cuts should be made first with fewest pieces possible.
    So we sort both horizontal and vertical. We pick the largest, make the cut, increase the pieces.
    Same solution as MinCostToCutCake1. Only difference is long instead of int.
    */
    public long minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        int i = m-2; //3 horizontal will have 2 in array, ie 0,1 index.
        int j = n-2;

        long horiPiece = 1;
        long vertiPiece = 1;
        long cost = 0;

        while(i >= 0 && j >= 0) {
            if(horizontalCut[i] > verticalCut[j]) { //When horizontal cut is made, it is made against each vertical piece. Horizontal piece count increases.
                cost += horizontalCut[i] * vertiPiece;
                horiPiece++;
                i--;
            }else { //When vertical cut is made, it is made against each horizontal piece. Vertical piece count increases.
                cost += verticalCut[j] * horiPiece;
                vertiPiece++;
                j--;
            }
        }

        while(i >= 0) {
            cost += horizontalCut[i] * vertiPiece;
            i--;
        }

        while(j >= 0) {
            cost += verticalCut[j] * horiPiece;
            j--;
        }

        return cost;
    }
}

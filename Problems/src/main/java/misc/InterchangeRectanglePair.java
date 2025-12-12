package misc;

import java.util.HashMap;
import java.util.Map;

public class InterchangeRectanglePair {
    /*
    https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles/description/
     */

    public long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Integer> ratioCount = new HashMap<Double, Integer>();
        long pairs = 0;

        for(int[] rect : rectangles) {
            double ratio = (double)rect[0]/rect[1];
            ratioCount.put(ratio, ratioCount.getOrDefault(ratio, 0)+1);
            pairs += ratioCount.get(ratio)-1;
        }

        return pairs;
    }
}

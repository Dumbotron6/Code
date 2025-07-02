package greedy;

import java.util.HashMap;
import java.util.Map;

public class RabbitsInForest {
    /*
    https://leetcode.com/problems/rabbits-in-forest/
     */

    //See GroupPeople first. Builds on that. Can be optimised to use array instead of map as constraint is small.
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> uniqueColors = new HashMap<Integer, Integer>();
        int total = 0;

        /*
        Why answer+1? If 1 rabbit says 1, then there are 2 rabbits of the same color.
        If 2 rabbits say 1, then there are 2 rabbits of the same color. 4 if 3 rabbits say 1 and 4 if 4 rabbits say 1.
        It is similar for 3 for example. If a rabbit says 3, then there are 4 rabbits.
        Below is the relation for 3. 1-3, 2-4, 3-4, 4-4, 5-4, 6-4 etc.
        Let's take 5 rabbits saying 3.
        For every count of a (count/color), there are (count/color) * color rabbits where color = answer+1;
        Going by above example, that's (5/4)*4 = 4 rabbits, plus one extra rabbit says 3, so 4+4 = 8 rabbits.
        */
        for(int answer : answers) {
            uniqueColors.put(answer+1, uniqueColors.getOrDefault(answer+1, 0)+1);
        }

        for(Map.Entry<Integer, Integer> uniqueColor : uniqueColors.entrySet()) {
            int color = uniqueColor.getKey();
            int count = uniqueColor.getValue();

            total += (count/color) * color;
            if(count%color > 0) {
                total += color;
            }
        }

        return total;
    }
}

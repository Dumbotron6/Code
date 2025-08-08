package arrays;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {
    /*
    https://leetcode.com/problems/fruit-into-baskets/
     */

    /*
    Basically find the longest continuous sub array that has exactly 2 distinct elements.
    Maintain a frequency count of trees encountered. When we encounter third tree, move left pointer till we have only two.
    */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> freqCnt = new HashMap<Integer, Integer>();
        int left = 0, right = 0;
        int trees = 0, maxTrees = 0;

        while(right < fruits.length) {
            freqCnt.put(fruits[right], freqCnt.getOrDefault(fruits[right], 0)+1);
            while(freqCnt.size() > 2) {
                int cnt = freqCnt.get(fruits[left]);
                if(cnt == 1) {
                    freqCnt.remove(fruits[left]);
                }else {
                    freqCnt.put(fruits[left], cnt-1);
                }
                left++;
                trees--;
            }
            trees++;
            right++;
            maxTrees = Math.max(trees, maxTrees);
        }

        return maxTrees;
    }
}

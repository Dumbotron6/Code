package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HandOfStraights {
    /*
    https://leetcode.com/problems/hand-of-straights/
     */

    //Same as DivideArrayKSets but that is better as we don't have to use heap which increases time complexity.
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> numCount = new HashMap<Integer, Integer>();
        /*
        We use priority queue so the numbers will be stored in
            sorted order, and we can do num+i down below to look for consecutive numbers.
         */
        PriorityQueue<Integer> allNums = new PriorityQueue<Integer>();

        for(int h : hand) {
            if(numCount.containsKey(h)) {
                numCount.put(h, numCount.get(h)+1);
            }else {
                numCount.put(h, 1);
                allNums.add(h);
            }
        }
        /*
        For each number, loop and find it's consecutive numbers till groupSize. If not found, return false.
        We can have multiples of the same number, ex. [1,1,2]. Which is why we loop numCount.containsKey(num).
        Once that num is used up, we reduce its count.
        */
        while(!allNums.isEmpty()) {
            int num = allNums.peek();
            while(numCount.containsKey(num)) {
                int i = 0;
                while(i < groupSize) {
                    int key = num+i;
                    if(numCount.containsKey(key)) {
                        if(numCount.get(key) > 1) {
                            numCount.put(key, numCount.get(key)-1);
                        }else {
                            numCount.remove(key);
                        }
                    }else {
                        return false;
                    }
                    i++;
                }
            }
            allNums.poll();
        }
        return true;
    }

    /*
    NOTE: Instead of Map PriorityQueue, we can also use List<int[]> count = new ArrayList<>();
        but we'd have to sort the hand array beforehand. so that it is ordered in the list to check num+i.
     */
}

package arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestConsequtiveSequence {
    /*
    https://leetcode.com/problems/longest-consecutive-sequence/description/
     */

    public int longestConsecutive(int[] nums) {
        Set<Integer> setNums = new HashSet<Integer>();

        for(int num : nums) {
            setNums.add(num);
        }

        int cnt = 1, max = 0;
        Map<Integer, Integer> seen = new HashMap<Integer, Integer>(); //Number, count if this number is the start of the sequence.

        for(int num : setNums) {
            if(!seen.containsKey(num)) {
                int val = num+cnt;
                while(setNums.contains(val)) { //Keep looping till set has next number.
                    if(seen.containsKey(val)) { //Next num as start already encountered, add its count.
                        cnt += seen.get(val);
                        break;
                    }
                    seen.put(val, 1);
                    cnt++;
                    val++;
                }
                seen.put(num, cnt);
                max = Math.max(cnt, max);
                cnt = 1;
            } //This if condition is to skip numbers already covered, whether as the start, or part of another sequence.
        }

        return max;
    }

    /*
    Take for example, [1,2,3,4]. Now when at index 0, the while loop would insert count 1 for 2,3,4 while after the loop,
        it would insert count 4 for 1.
    Now it doesn't matter that when we move to index 1 the count is stored as 1, as it was already covered by a number smaller than it.
    */

    /*
    Below solution simply skips the 'seen' logic so it would check every num as if it's the 'start'.
    But it's slightly faster simply because it avoids the map lookup overhead. Logically, above should be faster.
    This enters O(N2(square)) time complexity while above is O(N);
    Above logic built upon this logic, as the 'seen' is introduced to reduce the time complexity.
     */
    public int longestConsecutiveAlt(int[] nums) {
        Set<Integer> setNums = new HashSet<Integer>();

        for(int num : nums) {
            setNums.add(num);
        }

        int cnt = 1, max = 0;

        for(int num : setNums) {
            int val = num+cnt;
            while(setNums.contains(val)) {
                cnt++;
                val++;
            }
            max = Math.max(cnt, max);
            cnt = 1;
        }

        return max;
    }

    /*
    This is optimal as it avoids above recheck of 'start'.
    if (!numSet.contains(num - 1)) ensures it only checks next numbers if no immediate smaller number exists,
        ie, it's the start of a sequence.
    Time complexity is O(N);
    */
    public int longestConsecutiveOptimal(int[] nums) {
        Set<Integer> setNums = new HashSet<Integer>();

        for(int num : nums) {
            setNums.add(num);
        }

        int cnt = 1, max = 0;

        for(int num : setNums) {
            if(!setNums.contains(num-1)) { //This if condition is to start checking subsequent number only if this number is the start if a sequence.
                int val = num+cnt;
                while(setNums.contains(val)) { //Keep looping till set has next number.
                    cnt++;
                    val++;
                }
                max = Math.max(cnt, max);
                cnt = 1;
            }
        }

        return max;
    }
}

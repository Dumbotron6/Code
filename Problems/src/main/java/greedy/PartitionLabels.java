package greedy;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {
    /*
    https://leetcode.com/problems/partition-labels/
     */

    public List<Integer> partitionLabels(String s) {
        int lastMax = -1;
        int currMax = -1;

        //Keep track of the maximum poition of each alphabet.
        int[] maxTrack = new int[26];
        for(int i = 0; i < s.length(); i++) {
            maxTrack[s.charAt(i)-'a'] = i;
        }

        List<Integer> partLengths = new ArrayList<Integer>();
        /*
        When iterating, keep track of the maximum poision of the alphabets encountered so far.
        If we reach the current max, then it means there is no repeats after this.
        So we can safely add that to the return list.
        */
        for(int i = 0; i < s.length(); i++) {
            int maxPos = maxTrack[s.charAt(i)-'a'];
            if(maxPos > currMax) {
                currMax = maxPos;
            }
            if(currMax == i) {
                partLengths.add(i-lastMax);
                lastMax = currMax;
            }
        }
        return partLengths;
    }
}

package arrays;

import java.util.*;

public class TopKFrequentElements {
    /*
    https://leetcode.com/problems/top-k-frequent-elements/
     */

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> countTrack = new HashMap<Integer, Integer>();

        //Counting the frequency.
        for(int num : nums) {
            countTrack.merge(num, 1, Integer::sum); //This puts 1 as default value and sums the value if it exists.
            //countTrack.computeIfPresent(num, (k,v) -> v+1); can be used but there's no way to pass default value.
        }

        //counts[] tracks all elements at each frequency. freq[] is the return array.
        int[] freq = new int[k];
        List<Integer>[] counts = new List[nums.length+1];

        /*
        Take each number and assign it to it's respective frequency in counts[].
        Since there can be multiple elements of same frequency, we use a list.
        */
        for(int key : countTrack.keySet()) {
            if(counts[countTrack.get(key)] != null) {
                counts[countTrack.get(key)].add(key);
            }else {
                List<Integer> al = new ArrayList<Integer>();
                al.add(key);
                counts[countTrack.get(key)] = al;
            }
        }

        /*
        At each frequency, if there exists a list, loop through it and assign it in freq.
        In counts[], we go backwards from highest to lowest.
        */
        int c = counts.length-1;
        while(k > 0) {
            if(counts[c] != null) {
                for(int ele : counts[c]) {
                    freq[--k] = ele;
                }
            }
            c--;
        }

        return freq;
    }
}

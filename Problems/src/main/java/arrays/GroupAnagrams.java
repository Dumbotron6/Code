package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    /*
    https://leetcode.com/problems/group-anagrams/
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        //HashMap key will be the sorted string. If an anagram is found, the sorted string will be the same.
        HashMap<String, List<String>> gramTrack = new HashMap<String, List<String>>();
        List<List<String>> finalGrams = new ArrayList<List<String>>();

        //String is sorted to find key. Why finalGrams.add() in the else? Because the list is common in gramTrack and finalGrams.
        //So when gramTrack.get(key).add(strs[i]) is done, it is added to the list.
        for(int i = 0; i < strs.length; i++) {
            char[] str = strs[i].toCharArray();
            Arrays.sort(str);
            String key = new String(str);
            if(gramTrack.containsKey(key)) {
                gramTrack.get(key).add(strs[i]);
            }else {
                List<String> newList = new ArrayList<String>();
                newList.add(strs[i]);
                gramTrack.put(key, newList);
                finalGrams.add(newList);
            }
        }

        return finalGrams;
    }
}

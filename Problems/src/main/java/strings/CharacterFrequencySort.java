package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterFrequencySort {
    /*
    https://leetcode.com/problems/sort-characters-by-frequency/description/
     */

    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();

        for(int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        /*
        The reason for s.length+1 is, if the string is aaaa, then s.length is 4 and the array would be indexed 0 to 3.
        By having s.length+1, we'd have index 0 t0 4.
         */
        List<Character>[] charArray = new ArrayList[s.length()+1];

        /*
        If the string is aaaa, we'd have [[],[],[],[],[a]].
        If the string is aabb, we'd have [[][],[a,b],[],[]].
         */
        for(Character c : freqMap.keySet()) {
            int freq = freqMap.get(c);
            if(charArray[freq] == null)
                charArray[freq] = new ArrayList<Character>();
            charArray[freq].add(c);
        }

        StringBuilder freqString = new StringBuilder();
        for(int i = charArray.length-1; i >= 0; i--) {
            if(charArray[i] != null) {
                for(Character c : charArray[i]) {
                    freqString.append(String.valueOf(c).repeat(i));
                }
            }
        }

        return freqString.toString();
    }
}

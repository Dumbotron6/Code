package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortCharacterByFrequency {
    /*
    https://leetcode.com/problems/sort-characters-by-frequency/description/
     */

    /*
    An array of the size - maximum possible frequency is created, which is s.length()+1.
    In that array, we store a list of characters with their counts.
    Then we iterate through them backwards(for max to least) and put them in a string.
     */
    public String frequencySort(String s) {
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();

        for(int i = 0; i < s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        List<Character>[] charArray = new ArrayList[s.length()+1];

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

package misc;

public class MaximizeDifference1 {
    /*
    https://leetcode.com/problems/maximum-difference-between-even-and-odd-frequency-i/
     */

    public int maxDifference(String s) {
        int[] freq = new int[26];
        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)-'a']++;
        }

        int oddMax = 0;
        int evenMin = 103;

        for(int i = 0; i < 26; i++) {
            if(freq[i] > 0) {
                if(freq[i] %2 == 0) {
                    evenMin = Math.min(evenMin, freq[i]);
                }else {
                    oddMax = Math.max(oddMax, freq[i]);
                }
            }
        }

        return oddMax-evenMin;
    }
}

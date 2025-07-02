package strings;

public class SumBeautyOfSubstring {
    /*
    https://leetcode.com/problems/sum-of-beauty-of-all-substrings/description/
     */

    /*
    An array to keep count is created for each substring.
    Then max and min are found.
     */
    public int beautySum(String s) {
        int sum = 0;
        for(int i = 0; i < s.length(); i++) {
            int[] map = new int[26];
            for(int j = i; j < s.length(); j++) {
                map[s.charAt(j)-'a']++;
                sum += findBeauty(map);
            }
        }
        return sum;
    }

    public int findBeauty(int[] map) {
        int max = 0;
        int min = 501;
        for(int e : map) {
            if(e > 0) {
                max = Math.max(max, e);
                min = Math.min(min, e);
            }
        }
        return max-min;
    }
}

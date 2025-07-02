package slidingWindow;

public class LongestRepeatCharacterReplace {
    /*
    https://leetcode.com/problems/longest-repeating-character-replacement/description/
     */

    /*
    max is the number of same characters in the substring. ie, in "ABBBC", max would be 3.
    so end-begin+1 should be k+max where k is the number of chars to be replaced.
    This gives us (end-begin+1) == k. For decreasing the sliding window, == would become >.
    The reason we don't change max inside the inner loop is because, until that point, we encounter same chars in a
        substring > max, there is no point in checking anything less than max. So we change max only when we get
        something greater.
     */
    public int characterReplacement(String s, int k) {
        int begin = 0;
        int end = 0;
        int[] tracker = new int[26];
        int max = 0;
        int returnVal = 0;

        while(end < s.length()) {
            int temp = s.charAt(end) - 'A';
            tracker[temp]++;
            max = Math.max(max, tracker[temp]);

            while((end-begin+1) - max > k ) {
                int temp2 = s.charAt(begin) - 'A';
                tracker[temp2]--;
                begin++;
            }
            returnVal = Math.max(returnVal, end-begin+1);
            end++;
        }
        return returnVal;
    }
}

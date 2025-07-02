package slidingWindow;

import java.util.Arrays;

public class SubstringWithThreeCharacters {
    /*
    https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/
     */

    /*
    Once all 3 characters have been encountered, every element in trackLastPos will become > -1.
    Now whenever we encounter a new element, we simply update it's last position.
    This way, we'd get the earliest position of a,b or c without repeats.
    When s is "abca", when substring is "abc", the total would become (1+0) 1. Then, when the substring is "abca",
        the total would become 3 (total + 1 + 1[earliest of a,b,c]).
    Now why is total that value? Take "aaabc". When all a,b,c are found, the array would be [2,3,4].
    The min is 2. So from 2, "abc" exists. However, "aabc" and "aaabc" are also valid. So that's substrings starting
        form 0,1 which is nothing but 2(the earliest). If the substring is "aaaabc", it would be from 0,1,2 which is
        nothing but 3(the earliest). So we do 1 + all previous substrings.
     */
    public int numberOfSubstrings(String s) {
        int[] trackLastPos = new int[3];
        int total = 0;
        Arrays.fill(trackLastPos, -1);

        for(int i = 0; i < s.length(); i++) {
            trackLastPos[s.charAt(i) - 'a'] = i;

            if(trackLastPos[0] > -1 && trackLastPos[1] > -1 && trackLastPos[1] > -1) {
                total += (1 + Math.min(trackLastPos[0], Math.min(trackLastPos[1], trackLastPos[2])));
            }
        }
        return total;
    }

    /*
    This approach is far simpler but takes O(2N) instead of O(N).
    Here, when all 3 characters are encountered, every substring from that point till the end of string will have
        a, b and c. So that's what we calculate.
     */
    public int numberOfSubstringsAlt(String s) {
        int[] tracker = new int[3];
        int begin = 0;
        int end = 0;
        int total = 0;

        while(end < s.length()) {
            tracker['c' - s.charAt(end)]++;

            while(tracker[0] > 0 && tracker[1] > 0 && tracker[2] > 0) {
                total += (s.length()-end);
                tracker['c' - s.charAt(begin)]--;
                begin++;
            }
            end++;
        }
        return total;
    }
}

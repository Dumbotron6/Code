package stack;

public class RemoveAllOccurrenceSubstring {
    /*
    https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/
     */

    /*
    This is using the concept of stack without actually using stack data structure.
    When the final character of part is encountered in s, go backward checking if other characters match.
    If part is exhausted (j < 0), then that means part was found in result. So remove that substring.
    To simplify, we can also use substring if it's allowed instead of the whole while loop.
    Or we can use stack data structure to store each character but popping and re-adding into stack has to be done to check for part.
    */
    public String removeOccurrences(String s, String part) {
        int partLen = part.length()-1;
        int sLen = s.length();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < sLen; i++) {
            result.append(s.charAt(i));
            int resultLen = result.length();
            int j = partLen;
            int k = resultLen-1;
            while(k >= 0 && j >= 0 && result.charAt(k) == part.charAt(j)) {
                k--;
                j--;
            }
            if(j < 0) {
                result.delete(resultLen-partLen-1, resultLen+1);
            }
        }

        return result.toString();
    }
}

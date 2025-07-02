package strings;

public class MergeStringsAlternately {
    /*
    https://leetcode.com/problems/merge-strings-alternately/
     */

    public String mergeAlternately(String word1, String word2) {
        int i = 0;
        int j = 0;
        int len1 = word1.length();
        int len2 = word2.length();
        StringBuilder result = new StringBuilder();

        while(i < len1 && j < len2) {
            if(i == j) {
                result.append(word1.charAt(i++));
            }else {
                result.append(word2.charAt(j++));
            }
        }

        while(i < len1) {
            result.append(word1.charAt(i++));
        }
        while(j < len2) {
            result.append(word2.charAt(j++));
        }

        return result.toString();
    }
}

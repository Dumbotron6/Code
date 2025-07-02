package strings;

public class TotalCharactersStringTransform1 {
    /*
    https://leetcode.com/problems/total-characters-in-string-after-transformations-i/
     */

    public int lengthAfterTransformations(String s, int t) {
        int[] count = new int[26];
        int mod = (int)1_000_000_007;
        //Take frequency of each alphabet initially.
        for(int i = 0; i < s.length(); i++) {
            count[s.charAt(i)-'a']++;
        }

        //Enumerate till t, and at each iteration, take frequency count again.
        for(int i = 0; i < t; i++) {
            int[] newCount = new int[26];
            for(int j = 0; j < 25; j++) { //'a' to 'y'
                newCount[j+1] = count[j];
            }
            //'z' becomes 'a','b';
            newCount[0] = count[25];
            newCount[1] = (newCount[1]+count[25])%mod;
            count = newCount;
        }

        int len = 0;
        for(int i = 0; i < 26; i++) {
            len = (len+count[i])%mod;
        }

        return len;
    }
}

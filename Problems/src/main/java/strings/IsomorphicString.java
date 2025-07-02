package strings;

public class IsomorphicString {
    /*
    https://leetcode.com/problems/isomorphic-strings/description/

    Two strings are isomorphic when the characters of one string map to exactly one character of the other.
    For example, in [PAPER, TITLE], P-T, A-I, E-L and R-E. This is isomorphic. [FOO, BAR] is not.
     */
    public boolean isIsomorphic(String s, String t) {
        /*
        As per problem statement, the strings consist of valid ascii characters. Ascii has 256 chars.
        So we take two char arrays of ascii size.
         */
        char[] sTrack = new char[256];
        char[] tTrack = new char[256];

        for(int i = 0; i < s.length(); i++) {
            /*
            When new char is encountered in s, that char counterpart in the t is stored and vice versa.
            Ideally, in an isomorphic string, if a char in s is placed in the array, so will its counterpart in t.
            When we encounter a char in whose value is stored in s array but not t array or vice versa,
                then it means we have a mismatch. ie, one is 0 (ascii for null) and the other is not.

            Instead of char array, we can also use int array and store the position of char encountered.
            But this would mean initializing the char array values to -1 as it can't be 0 (0 would be a position).
             */
            //both aren't see before
            if(sTrack[s.charAt(i)] == 0 && tTrack[t.charAt(i)] == 0) {
                sTrack[s.charAt(i)] = t.charAt(i);
                tTrack[t.charAt(i)] = s.charAt(i);
            //one is seen and the other isn't
            }else if(sTrack[s.charAt(i)] != t.charAt(i) || tTrack[t.charAt(i)] != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}

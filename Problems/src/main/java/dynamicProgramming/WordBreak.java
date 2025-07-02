package dynamicProgramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    /*
    https://leetcode.com/problems/word-break/description/
     */

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> hSet = new HashSet<String>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return findWords(s, hSet, 0, memo);
    }

    /*
    This uses 'memoization'.
    The idea is to remember if a position was previously checked or not.
    For example, in 'barktire', ['ba','rk','bark','type'], 'ba' and 'rk' are found. Now 'start' is at 't'.
    The loop checks for 't', 'ti', 'tir' and 'tire'. We reach the end of the string but find no matching word.
        So we mark the position 't' as false. Meaning, if we begin the word search in 't', there will be no word to be
        found.
    Now, the initial loop will continue and check 'bar' and 'bark'. 'bark' is found. Now start will become 't' again.
    There is no need to check from 't' again because we already checked it and marked it as false.
    Consider ['ba','rk','bark','type','barkt','ire']. Now, when we find 'bark', 't' will return false. So the loop moves
        to check 'barkt' which is found. The recursion will check 'i', 'ir' and 'ire' which is found.
     */
    public boolean findWords(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if(start == s.length()) {
            return true;
        }

        if(memo[start] != null) {
            return memo[start];
        }

        boolean iteratedButFound = false;
        for(int i = start+1; i <= s.length(); i++) {
            if(wordDict.contains(s.substring(start,i))) {
                if(findWords(s, wordDict, i, memo)) {
                    iteratedButFound = true;
                    break;
                }
            }
        }

        memo[start] = iteratedButFound;
        return memo[start];
    }
}

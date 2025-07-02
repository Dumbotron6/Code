package recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromeParition {
    /*
    https://leetcode.com/problems/palindrome-partitioning/
     */

    public List<List<String>> partition(String s) {
        List<List<String>> finalList = new ArrayList<List<String>>();
        addPartitions(s, 0, finalList, new ArrayList<String>());
        return finalList;
    }

    /*
    In this problem also, we are doing backtracking.
    We will only reach start == s.length() when all previous palindromes have been covered.
    For ex. in "aabb", "aab" will fail the isPalindrome check and will not make any further recursive calls.
    finalList.add does a fresh copy of the existing list. So we can safely pop the last element added in partList
        during backtrack.
    For example, the order of recursion is, [a,a,b,b], [a,a,bb], [a,abb], [aa,b,b], [aa,bb], [aab,b], [aabb].
    Here, [a,abb] and [aab,b] will not make recursive calls as they fail isPalindrome and hence not be added to the
        finalList. So, [aab,b] list will not happen as it will not make recursive call while string is aab and instead
        move on the check aabb.
     */
    public void addPartitions(String s, int start, List<List<String>> finalList, List<String> partList) {
        if(start == s.length()) {
            finalList.add(new ArrayList<String>(partList));
        }else {
            for(int i = start; i < s.length(); i++) {
                if(isPalindrome(s, start, i)) {
                    partList.add(s.substring(start, i+1));
                    addPartitions(s, i+1, finalList, partList);
                    partList.removeLast();
                }
            }
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        while(start <= end) {
            if(s.charAt(start) != s.charAt(end))
                return false;
            start++; end--;
        }
        return true;
    }
}

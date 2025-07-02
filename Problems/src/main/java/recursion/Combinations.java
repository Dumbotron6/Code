package recursion;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
    /*
    https://leetcode.com/problems/combinations/
     */

    //Regular backtracking problem. Add a number, recurse, backtrack by removing the number.
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        backTrack(finalList, new ArrayList<Integer>(), 1, n, k);
        return finalList;
    }

    public void backTrack(List<List<Integer>> finalList, List<Integer> currList, int pos, int n, int k) {
        if(currList.size() == k) {
            finalList.add(new ArrayList<Integer> (currList));
            return;
        }

        for(int i = pos; i <= n; i++) {
            currList.add(i);
            backTrack(finalList, currList, i+1, n, k);
            currList.removeLast();
        }
    }
}

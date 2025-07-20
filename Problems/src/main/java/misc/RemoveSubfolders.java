package misc;

import java.util.*;

public class RemoveSubfolders {
    /*
    https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
     */

    /*
    Can apparently use Trie. But not used in this solution.
    If root folder exists, remove all subsequent sub folders.
    Sort the string array lexicographically.
    Add the root folder to a set, check if subsequent strings in array are sub folders, if not add to set.
    */
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        Set<String> uniqueFol = new HashSet<String>();

        for(String fold : folder) {
            int len = fold.length();
            int end = 1;
            while(end < len) {
                if(fold.charAt(end) == '/' && uniqueFol.contains(fold.substring(0, end))) {
                    break;
                }
                end++;
            }
            if(end == len) {
                uniqueFol.add(fold);
            }
        }

        return new ArrayList(uniqueFol);
    }
}

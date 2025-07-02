package greedy;

import java.util.Arrays;

public class AssignCookies {
    /*
    https://leetcode.com/problems/assign-cookies/description/
     */

    public int findContentChildren(int[] g, int[] s) {

        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;
        while(i < g.length && j < s.length) {
            if(g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        /*
        Returning i is enough as i only increases when a child is content. So that equals number of content children.
        We can also keep a count say, contentChildren and return that count.
         */
        return i;
    }
}

package stack;

import java.util.LinkedList;

public class RobotLexiString {
    /*
    https://leetcode.com/problems/using-a-robot-to-print-the-lexicographically-smallest-string/
     */

    public String robotWithString(String s) {
        int[] counter = new int[26];

        for(int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)-'a']++;
        }

        StringBuilder paperString = new StringBuilder();
        LinkedList<Character> stack = new LinkedList<Character>();

        char current = 'a';
        for(int i = 0; i < s.length(); i++) {
            //We remove current element and add it to the stack, reduce it's count.
            char c = s.charAt(i);
            counter[c -'a']--;
            stack.push(c);

            //We move to the character that has a count. The order or priority would be from a to z.
            while(current < 'z' && counter[current-'a'] == 0) {
                current++;
            }

            //Why do this? As long as there remanins a character that is less than current, that will have priority to be inserted to paperString.
            while(!stack.isEmpty() && stack.peek() <= current) {
                paperString.append(stack.pop());
            }

            /*
            By this example, taking "bdda"
            1. Stack - "b", counter[b] == 0, but counter[a] == 1, so current remains a.
            2 and 3. Stack - "bdd", counter[d] == 0, but counter[a] == 1, so current remains a.
            4. Stack = "bdda", counter[a] == 0, current will iterate and move to z.
            */

            /*
            By this example, taking "zzabdc"
            1 and 2. Stack - "zz", counter[z] == 0, but counter[a] == 1, so current remains a.
            3. Stack - "zza", counter[a] == 0, so current becomes b.
            3. Stack - "zzb", counter[b] == 0, so current becomes c.
            4. Stack -"zzd", counter[c] == 1, so current remains c.
            */

            /*So basically, if a higher priority character exists, that will be inserted.
                If not, the remaining values in the stack will be inserted in the string.
            */
        }

        return paperString.toString();
    }
}

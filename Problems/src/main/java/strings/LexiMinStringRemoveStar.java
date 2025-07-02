package strings;

import java.util.PriorityQueue;

public class LexiMinStringRemoveStar {
    /*
    https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/
     */

    public String clearStars(String s) {
        PriorityQueue<Integer>[] charIndex = new PriorityQueue[26];
        for(int i = 0; i < 26; i++) {
            charIndex[i] = new PriorityQueue<Integer>((a,b) -> b-a); //Order by max index.
        }
        int len = s.length();

        for(int i = 0; i < len; i++) {
            if(s.charAt(i) == '*') {
                removeSmallest(charIndex);
            }else {
                charIndex[s.charAt(i)-'a'].offer(i);
            }
        }

        StringBuilder result = new StringBuilder();

        for(int i = len-1; i >= 0; i--) {
            char c = s.charAt(i);
            if(c != '*' && !charIndex[c-'a'].isEmpty() && charIndex[c-'a'].peek() == i) {
                result.append(c);
                charIndex[c-'a'].poll();
            }
        }

        return result.reverse().toString();
    }

    //Iterate through and remove the max index of the smallest char first encountered.
    public void removeSmallest(PriorityQueue<Integer>[] charIndex) {
        for(int i = 0; i < 26; i++) {
            if(!charIndex[i].isEmpty()) {
                charIndex[i].poll();
                break;
            }
        }
    }
}

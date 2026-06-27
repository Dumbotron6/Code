package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class ReorganizeString {
    /*
    https://leetcode.com/problems/reorganize-string/
     */

    public String reorganizeString(String s) {
        int[][] freq = new int[26][2];

        for(int i = 0; i < 26; i++) {
            freq[i][0] = i;
        }
        for(char c : s.toCharArray()) {
            freq[c-'a'][1]++;
        }

        //Execute by highest frequency.
        Queue<int[]> heap = new PriorityQueue<int[]>((a,b) -> b[1]-a[1]);
        for(int i = 0; i < 26; i++) {
            if(freq[i][1] > 0) {
                heap.offer(freq[i]);
            }
        }

        int[] prev = null;
        StringBuilder result = new StringBuilder();

        while(prev != null || !heap.isEmpty()) {
            //If only one character left, it means it will end up being adjacent.
            if(prev != null && heap.isEmpty()) {
                return "";
            };

            int[] added = addToString(heap, result); //Don't add the currently added char to heap so that chars don't become adjacent.

            if(prev != null) { //Since new char added, char added in prev iteration can be added back to heap.
                heap.offer(prev);
                prev = null;
            }

            prev = added;
        }

        return result.toString();
    }

    public int[] addToString(Queue<int[]> heap, StringBuilder result) {
        int[] arr = heap.poll();
        arr[1]--;

        result.append((char)('a'+arr[0]));
        return arr[1] > 0 ? arr : null;
    }

    //Identical approach to above. Difference is, adding to heap and then removing if same as prev char is done here, which is unnecessary.
    public String reorganizeStringAlt(String s) {
        int[][] freq = new int[26][2];

        for(int i = 0; i < 26; i++) {
            freq[i][0] = i;
        }
        for(char c : s.toCharArray()) {
            freq[c-'a'][1]++;
        }

        //Execute by highest frequency.
        Queue<int[]> heap = new PriorityQueue<int[]>((a,b) -> b[1]-a[1]);
        for(int i = 0; i < 26; i++) {
            if(freq[i][1] > 0) {
                heap.offer(freq[i]);
            }
        }

        int prevIndex = -1;
        StringBuilder result = new StringBuilder();

        while(!heap.isEmpty()) {
            //If only one character left, it means it will end up being adjacent.
            if(heap.size() == 1 && heap.peek()[1] > 1) {
                return "";
            };

            if(heap.peek()[0] != prevIndex) { //Add only if not same as previous char.
                prevIndex = addToStringAlt(heap, result);
            }else { //Add the next highest freq char, add this back to heap.
                int[] temp = heap.poll();
                prevIndex = addToStringAlt(heap, result);
                heap.offer(temp);
            }
        }

        return result.toString();
    }

    public int addToStringAlt(Queue<int[]> heap, StringBuilder result) {
        int[] arr = heap.poll();
        arr[1]--;
        if(arr[1] > 0) {
            heap.offer(arr);
        }
        result.append((char)('a'+arr[0]));
        return arr[0];
    }
}

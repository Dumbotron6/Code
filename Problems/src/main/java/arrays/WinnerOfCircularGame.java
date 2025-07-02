package arrays;

import java.util.LinkedList;

public class WinnerOfCircularGame {
    /*
    https://leetcode.com/problems/find-the-winner-of-the-circular-game/description/
     */

    public int findTheWinner(int n, int k) {
        DLL head = new DLL(1, null, null); //Make a doubly linked list with head as 1;
        DLL prev = head;
        for(int i = 2; i <= n; i++) {
            DLL next = new DLL(i, prev, null); //Create a DLL till n.
            prev.next = next; //Assign to previously created DLL.
            prev = next; //Make newly created DLL previous.
        }
        //Prev will be the last DLL created. Link it to head.
        prev.next = head;
        head.prev = prev;

        /*
        Note that since first and last DLL are connected, if n == 1, only one DLL will be created
            and it will link to itself. So we can infer that only one DLL exists.
        We use the same logic to keep looping till we are left with only one DLL.
        */
        while(head.next != head) {
            for(int i = 1; i < k; i++) { //Essentially stopping at k-1. prev will be k-1 and head will be k, which needs to be removed.
                prev = head;
                head = head.next;
            }
            prev.next = head.next; //Assign k+1 as k-1 next.
            head.next.prev = prev; //Assign k-1 as k+1 prev.
            head = head.next; //Move head to k+1.
        }

        return head.num;
    }

    //Building on previous solution, we use queue. This is worse than the above solution.
    public int findTheWinnerQueue(int n, int k) {
        LinkedList<Integer> head = new LinkedList<Integer>();

        for(int i = 1; i <= n; i++) {
            head.add(i);
        }

        //Keep popping and adding to queue till k is reached, upon which pop and don't add.
        while(head.size() != 1) {
            for(int i = 1; i < k; i++) {
                head.add(head.pop());
            }
            head.pop();
        }

        return head.peek();
    }

    class DLL {
        int num;
        DLL prev;
        DLL next;

        public DLL(int num, DLL prev, DLL next) {
            this.num = num;
            this.prev = prev;
            this.next = next;
        }
    }
}

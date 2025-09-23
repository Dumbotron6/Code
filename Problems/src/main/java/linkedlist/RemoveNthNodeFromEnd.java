package linkedlist;

public class RemoveNthNodeFromEnd {
    /*
    https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pos = new ListNode();
        ListNode top = pos;
        pos.next = head;
        ListNode nth = head;
        int i = 1;

        //Have nth at n distance from pos.
        while(i < n) {
            nth = nth.next;
            i++;
        }

        //Have pos and nth maintain n distance till the end.
        while(nth.next != null) {
            pos = pos.next;
            nth = nth.next;
        }

        pos.next = pos.next.next;

        return top.next;
    }

    public ListNode removeNthFromEndAlt(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        int count = 0;

        //Move second only when count has become n. Now first and second will maintain n distance.
        while(first != null) {
            first = first.next;
            if(count > n) {
                second = second.next;
            }
            count++;
        }

        //count also gives size
        //when not first element of linked list to be removed
        if(count != n)
            second.next = second.next.next;
        else
            head = head.next;

        return head;
    }
}

package linkedlist;

public class PartitionList {
    /*
    https://leetcode.com/problems/partition-list/
     */

    //We make two lists, one smaller and one greater, then combine them in the end.
    public ListNode partition(ListNode head, int x) {
        ListNode smaller = new ListNode();
        ListNode smallHead = smaller;
        ListNode greater = new ListNode();
        ListNode greatHead = greater;

        while(head != null) {
            if(head.val < x) {
                smaller.next = head;
                smaller = head;
            }else {
                greater.next = head;
                greater = head;
            }
            head = head.next;
        }
        greater.next = null; //Otherwise could lead to cycle.

        smaller.next = greatHead.next;

        return smallHead.next;
    }
}

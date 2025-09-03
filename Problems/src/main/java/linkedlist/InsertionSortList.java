package linkedlist;

public class InsertionSortList {
    /*
    https://leetcode.com/problems/insertion-sort-list/description/
     */

    public ListNode insertionSortList(ListNode head) {
        ListNode top = new ListNode();
        top.next = head;
        ListNode pos = head.next; //Start from second node.
        ListNode prev = head;

        while(pos != null) {
            if(pos.val < prev.val) { //Check immediate previous node. This check eliminates checking already sorted nodes.
                ListNode topPrev = top;
                ListNode check = top.next; //Check from the beginning.
                while(check.val < pos.val) { //Loop till we find position.
                    topPrev = check;
                    check = check.next;
                }
                pos = insert(topPrev, check, pos, prev);
            }
            prev = pos;
            pos = pos.next;
        }

        return top.next;
    }

    //Take pos, insert between topPrev and check. Point prev.next to pos.next since pos is being moved.
    public ListNode insert(ListNode topPrev, ListNode check, ListNode pos, ListNode prev) {
        prev.next = pos.next;
        topPrev.next = pos;
        pos.next = check;

        return prev;
    }
}

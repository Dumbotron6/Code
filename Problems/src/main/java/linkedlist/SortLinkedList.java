package linkedlist;

public class SortLinkedList {
    /*
    https://leetcode.com/problems/sort-list/
     */

    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    public ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode mid = findMid(head);
        ListNode midNext = mid.next;
        //doing this as the tail.next should always point to null
        //makes is easier to know when the linked list is ending
        mid.next = null;
        head = mergeSort(head);
        midNext = mergeSort(midNext);
        return merge(head, midNext);
    }

    public ListNode merge(ListNode head, ListNode mid) {
        ListNode initialList = new ListNode();
        ListNode returnList = initialList;

        while(head != null && mid != null) {
            if(head.val < mid.val) {
                returnList.next = head;
                returnList = returnList.next;
                head = head.next;
            } else {
                returnList.next = mid;
                returnList = returnList.next;
                mid = mid.next;
            }
        }

        if(mid == null)
            returnList.next = head;
        else
            returnList.next = mid;

        return initialList.next;

    }

    public ListNode findMid(ListNode head) {
        ListNode slow, fast;
        slow = head;
        //usually, this would be fast = head
        //not so here as [4,3,2,1] would result in 2 as the mid instead of 3
        fast = head.next;

        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}

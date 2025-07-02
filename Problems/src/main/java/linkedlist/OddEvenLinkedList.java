package linkedlist;

public class OddEvenLinkedList {
    /*
    https://leetcode.com/problems/odd-even-linked-list/
     */

    //Can also be solved like PartitionList. That is easier to understand.
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode oddPointer = head;
        ListNode evenPointer, evenHead;
        evenPointer = evenHead = head.next;

        //checking only evenPointer as evenPointer will always be ahead of oddPointer
        //regardless of if the no. of elements is odd or even
        //ex. for[1,2,3,4,5,6], headPointer will be 6 and for [1,2,3,4,5], it will be null(4.next.next)
        while(evenPointer != null && evenPointer.next != null) {
            oddPointer.next = oddPointer.next.next;
            evenPointer.next = evenPointer.next.next;

            oddPointer = oddPointer.next;
            evenPointer = evenPointer.next;
        }

        oddPointer.next = evenHead;

        return head;
    }
}

package linkedlist;

public class ReorderList {

    /*
    https://leetcode.com/problems/reorder-list/
     */

    public void reorderList(ListNode head) {
        ListNode secondHead = head;
        ListNode secondPtr = head.next;

        //Find the middle of the linkedlist.
        while(secondPtr != null && secondPtr.next != null) {
            secondHead = secondHead.next;
            secondPtr = secondPtr.next.next;
        }

        /*
        Split the LL into two from the middle.
        Assign the next element from  middle as the second LL and make the tail of first LL point to null.
        */
        ListNode prev = secondHead.next;
        secondHead.next = null;
        secondHead = prev;
        prev = null;

        //Reverse the second LL.
        while(secondHead != null) {
            ListNode temp = secondHead.next;
            secondHead.next = prev;
            prev = secondHead;
            secondHead = temp;
        }
        secondHead = prev;

        //Combine the two LL alternatively.
        ListNode firstHead = head;
        ListNode finalHead = new ListNode();
        while(secondHead != null) {
            ListNode temp1 = firstHead.next;
            ListNode temp2 = secondHead.next;
            finalHead.next = firstHead;
            firstHead.next = secondHead;
            firstHead = temp1;
            secondHead = temp2;
            finalHead = finalHead.next.next;
        }
        finalHead.next = firstHead;
        /*
        Why do this? Because if second LL has ended but there are elements still left in
            the first LL, then that has to be appended to the final result.
        Since we are splitting the original LL in the middle, second will never be greater than first.
        First will have +1 at most, if there are odd number of elements.
        */
    }
}

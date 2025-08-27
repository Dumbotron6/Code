package linkedlist;

public class PalindromeLinkedList {
    /*
    https://leetcode.com/problems/palindrome-linked-list/
     */

    public boolean isPalindrome(ListNode head) {
        ListNode first, second;
        first = second = head;
        //finding middle
        while(second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
        }
        second = first; //middle
        first = head;
        //reversing from middle to end, assiging middle.next as null
        second = reverseList(null, second);
        //comparing first and seond half
        while(second != null) {
            if(first.val != second.val)
                return false;
            first = first.next;
            second = second.next;
        }
        return true;
    }

    public ListNode reverseList(ListNode first, ListNode second) {
        if(second != null) {
            ListNode back = second.next;
            second.next = first;
            return reverseList(second, back);
        }
        return first;
    }
}

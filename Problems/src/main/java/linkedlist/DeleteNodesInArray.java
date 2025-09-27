package linkedlist;

import java.util.HashSet;
import java.util.Set;

public class DeleteNodesInArray {
    /*
    https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/description/
     */

    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> numSet = new HashSet<Integer>();

        for(int num : nums) {
            numSet.add(num);
        }

        ListNode top = new ListNode();
        top.next = head;

        ListNode ptr = top;

        while(ptr.next != null) {
            if(numSet.contains(ptr.next.val)) {
                ptr.next = ptr.next.next;
            }else {
                ptr = ptr.next;
            }
        }

        return top.next;
    }
}

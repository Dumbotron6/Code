package linkedlist;

public class MergeNodesBetweenZeros {
    /*
    https://leetcode.com/problems/merge-nodes-in-between-zeros/description/
     */

    /*
    Use two pointers. One to traverse node, one to keep track of node to be modified.
    When 0 is encountered, put the sum into node to be modified, make its next pointer point to node after 0.
    Then change the node to be tracked. Repeat.
    This is possible because we know there will always be a 0 at the beginning and the end. Plus there are no consecutive 0s.
    */
    public ListNode mergeNodes(ListNode head) {
        head = head.next;
        ListNode ptr = head;
        ListNode mod = head;
        int sum = 0;

        while(ptr != null) {
            if(ptr.val == 0) {
                mod.val = sum;
                sum = 0;
                mod.next = ptr.next;
                mod = mod.next;
            }else {
                sum += ptr.val;
            }
            ptr = ptr.next;
        }

        return head;
    }
}

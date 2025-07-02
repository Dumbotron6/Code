package linkedlist;

public class DeleteNodeInLL {
    /*
    https://leetcode.com/problems/delete-node-in-a-linked-list/description/
     */

    //This is not exactly delete. Problem statement is confusing. Just replace the node value with next node value.
    public void deleteNode(ListNode node) {
        ListNode prev = null;
        while(node.next != null) {
            node.val = node.next.val;
            prev = node;
            node = node.next;
        }
        prev.next = null;
    }
}

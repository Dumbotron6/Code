package linkedlist;

public class ReverseNodesInKGroup {
    /*
    https://leetcode.com/problems/reverse-nodes-in-k-group/description/
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        //Node that will be returned.
        ListNode retNode = new ListNode(0);
        retNode.next = head;

        //Keeping track of the kth node.
        ListNode kthNode = head;
        //Keeping track of the tail from where newly reversed node should be appended.
        ListNode oldTail = retNode;
        while(kthNode != null) {
            int count = 1;
            //The current head will become the old tail. So store it.
            ListNode tempTail = kthNode;
            //Till k nodes are found or end is reached.
            while(count != k && kthNode.next != null) {
                kthNode = kthNode.next;
                count++;
            }

            /*
            Store the node where next iteration should begin.
            Make kthNode.next be null so reversal can be done.
            Reverse the node and make old tail point to new head(only if count == k. It not, make it point to old head).
            The above is because we should leave nodes as is if count < k.
            Make new tail be the end of reversed node(which was the head before reversal).
            Assign the new head from where next iteration will begin.
            */
            ListNode tempHead = kthNode.next;
            kthNode.next = null;

            if(count == k) {
                oldTail.next = reverseList(tempTail);
            }else {
                oldTail.next = tempTail;
            }

            oldTail = tempTail;
            kthNode = tempHead;
        }

        return retNode.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode ptr = null;
        ListNode prev = null;
        while(head != null) {
            ptr = head.next;
            head.next = prev;
            prev = head;
            head = ptr;
        }
        return prev;
    }
}

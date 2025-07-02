package linkedlist;

public class SortZeroOneTwo {
    /*
    https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=given-a-linked-list-of-0s-1s-and-2s-sort-it
     */

    /*
    We add zeros, ones and twos to separate LLs and merge them at the end.
     */
    public Node segregate(Node head) {
        Node zeroTracker = new Node(-1);
        Node zeroHead = zeroTracker;
        Node oneTracker = new Node(-1);
        Node oneHead = oneTracker;
        Node twoTracker = new Node(-1);
        Node twoHead = twoTracker;
        Node pointer = head;

        while(pointer != null) {
            if(pointer.data == 0) {
                zeroTracker.next = pointer;
                zeroTracker = zeroTracker.next;
            }else if(pointer.data == 1) {
                oneTracker.next = pointer;
                oneTracker = oneTracker.next;
            }else if(pointer.data == 2) {
                twoTracker.next = pointer;
                twoTracker = twoTracker.next;
            }
            pointer = pointer.next;
        }

        if(oneHead.next != null)
            zeroTracker.next = oneHead.next;
        else
            zeroTracker.next = twoHead.next;
        oneTracker.next = twoHead.next;
        twoTracker.next = null;

        return zeroHead.next;
    }

    class Node
    {
        int data;
        Node next;
        Node(int data)
        {
            this.data = data;
            next = null;
        }
    }
}

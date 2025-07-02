package linkedlist;

public class AddOneToLL {
    /*
    https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=add-1-to-a-number-represented-as-linked-list
     */

    //Non-recursive and uses O(3N) time.
    public Node addOne(Node head) {
        Node prev = null;
        while(head != null) {
            Node temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }

        head = prev;
        prev = null;
        int carry = 1;
        while(head != null) {
            int val = head.data + carry;
            if(val > 9) {
                head.data = 0;
            }else {
                head.data = val;
                carry = 0;
            }
            Node temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }

        if(carry == 1) {
            Node newHead = new Node(1);
            newHead.next = prev;
            prev = newHead;
        }

        return prev;
    }

    //Recursive and uses O(N) time. But space is O(N) as each call is stored in recursive stack space.
    public Node addOneRecursive(Node head) {
        int carry = addOneRecur(head);
        if(carry == 1) {
            Node newHead = new Node(1);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }

    public int addOneRecur(Node head) {
        if(head.next == null) {
            head.data += 1;
            if(head.data > 9) {
                head.data = 0;
                return 1;
            }
            return 0;
        }

        head.data += addOneRecur(head.next);
        if(head.data > 9) {
            head.data = 0;
            return 1;
        }
        return 0;
    }

    class Node{
        int data;
        Node next;

        Node(int x){
            data = x;
            next = null;
        }
    }

}

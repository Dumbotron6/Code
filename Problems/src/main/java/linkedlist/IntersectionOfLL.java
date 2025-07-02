package linkedlist;

public class IntersectionOfLL {
    /*
    https://leetcode.com/problems/intersection-of-two-linked-lists/
     */

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int aLen = 0;
        int bLen = 0;
        ListNode pointA = headA;
        ListNode pointB = headB;

        /*
        We find the lengths of the LLs and their difference. The reason being, say the LLs are [3,4,1,2] and [5,6,7,8,1,2],
            the LLs will intersect, at minimum, only of the head of the LL with the lower length. So by finding their
            difference and moving the higher length one till L1 and L2 length are equal ie, making both LLs same length,
            we can start looking for the intersection point.
         */
        while(pointA != null) {
            aLen++;
            pointA = pointA.next;
        }
        pointA = headA;

        while(pointB != null) {
            bLen++;
            pointB = pointB.next;
        }
        pointB = headB;

        if(aLen > bLen) {
            for(int i = 0; i < aLen-bLen; i++)
                pointA = pointA.next;
        } else {
            for(int i = 0; i < bLen-aLen; i++)
                pointB = pointB.next;
        }

        while(pointA != null) {
            if(pointA == pointB)
                return pointA;
            pointA = pointA.next;
            pointB = pointB.next;
        }

        return null;
    }
}

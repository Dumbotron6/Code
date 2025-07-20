package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class RemoveZeroSumConsecutiveNodes {
    /*
    https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/description/
     */

    /*
    We use prefixSum to determine which index to which index should be removed. If we encounter a prefixSum again, it means everything
        in between amounts to 0. So all of them can be removed.
    */
    public ListNode removeZeroSumSublists(ListNode head) {
        int prefixSum = 0;
        Map<Integer, ListNode> sumTrack = new HashMap<Integer, ListNode>();
        ListNode retHead = new ListNode(0, head);

        while(head != null) {
            prefixSum += head.val;
            sumTrack.put(prefixSum, head);
            head = head.next;
        }

        head = retHead;
        prefixSum = 0;

        while(head != null) {
            prefixSum += head.val;
            if(sumTrack.containsKey(prefixSum)) {
                head.next = sumTrack.get(prefixSum).next;
            }
            head = head.next;
        }

        return retHead.next;
    }

    /*
    Example: In [1,2,3,-1,-2,4], the prefix sum is [1,3,6,5,3,7]. Everything from index 2 to index 4 sums up to 0.
        That is why prefix sum becomes 3 again at index 4. So it's safe to say indexes 2 to 4 can be removed.
    In [1,3,2,-3,-2,5,5,-5,1], the prefixSum is [1,4,6,3,1,6,11,6,7]. Everything between 1 to (including)1 should be removed.
        Now 11,6 should be removed because there is already a 6. So essentially, we need to store the highest index of each prefix sum.
        Now when we iterate over the linked list again, we can directly skip over to the highest index node.
    */
}

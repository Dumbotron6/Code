package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedLists {
    /*
    https://leetcode.com/problems/merge-k-sorted-lists/
     */

    /*
    When an element is added to the pq, the least element is at the top.
    When that is removed(polled), the next least element takes its place.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<ListNode>((a, b) -> a.val-b.val);
        for(int i = 0; i < lists.length; i++) {
            ListNode head = lists[i];
            while(head != null) {
                pq.add(head);
                head = head.next;
            }
        }

        ListNode sortedList = new ListNode(0);
        ListNode ptr = sortedList;
        while(!pq.isEmpty()) {
            ptr.next = pq.poll();
            ptr = ptr.next;
        }
        ptr.next = null;

        return sortedList.next;
    }

    /*
    Using merge sort. More efficient.
    Instead of comparing the head of every list each time(brute force approach), we instead approach it two at a time.
    So we compare two lists, merge them, and then that combined list is merged in the previous recursive call.
    Why start > end and start == end? Take array [0,1] Now left merge would be merge(lists, 0, 0) and
        right merge would be merge(lists, 1 , 1).
    Now for array say, [1], left would be merge(lists, 1, 1) and right would be merge(lists, 2, 1).
    Once returned, in the above recursive call, we'd be comparing lists[1] and null.
    In the recursive call above that, we'd be comparing lists[0] and lists[1].
    In the recursive call above that, we'd be comparing merged lists[0], list[1] and say merged lists[2] and lists[3].
     */
    public ListNode mergeKListsUsingMergeSort(ListNode[] lists) {
        return merge(lists, 0, lists.length-1);
    }

    public ListNode merge(ListNode[] lists, int start, int end) {
        if(start > end)
            return null;
        if(start == end)
            return lists[start];

        int mid = start + (end-start)/2;
        ListNode left = merge(lists, start, mid);
        ListNode right = merge(lists, mid+1, end);
        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode left, ListNode right) {
        if(left == null)
            return right;
        if(right == null)
            return left;

        ListNode head = new ListNode(0);
        ListNode ptr = head;

        while(left != null && right != null) {
            if(left.val < right.val) {
                ptr.next = left;
                left = left.next;
            }else {
                ptr.next = right;
                right = right.next;
            }
            ptr = ptr.next;
        }

        if(left != null)
            ptr.next = left;
        if(right != null)
            ptr.next = right;

        return head.next;
    }
}

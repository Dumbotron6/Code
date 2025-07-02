package linkedlist;

public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length-1);
    }

    /*
    We use merge sort to take two LLs, merge them and return a single array.
    We do this recursively, so all LLs are merged into one at the end.
     */
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

    //For every two LLs, we formed a sorted single LL.
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

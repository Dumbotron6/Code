package tree.binarysearchtree;

import tree.TreeNode;

public class SortedArrayToBST {
    /*
    https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
     */

    public TreeNode sortedArrayToBST(int[] nums) {
        return binaryBuild(nums, 0, nums.length-1);
    }

    public TreeNode binaryBuild(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }

        int mid = start+(end-start)/2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = binaryBuild(nums, start, mid-1);
        node.right = binaryBuild(nums, mid+1, end);

        return node;
    }
}

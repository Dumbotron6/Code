package tree.binarysearchtree;

import tree.TreeNode;

import java.util.LinkedList;

public class KthSmallestElement {
    /*
    https://leetcode.com/problems/kth-smallest-element-in-a-bst/
     */

    //We find k-th smallest element using in-order traversal.
    public int kthSmallest(TreeNode root, int k) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        int count = 0;

        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                count++;
                if(count == k) {
                    return node.val;
                }
                node = node.right;
            }
        }

        return -1;
    }
}

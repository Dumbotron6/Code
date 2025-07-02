package tree.binarysearchtree;

import tree.TreeNode;

import java.util.LinkedList;

public class ValidBST {
    /*
    https://leetcode.com/problems/validate-binary-search-tree/description/
     */

    /*
    We use in-order traversal and keep track of previous value ie, the most recent pop value.
    Since we pop only when we move right, that popped will be the 'root' of the below subtree.
    Now that 'root' will have to be smaller than everything to the right, ie, current node we are popping.
    If there are only elements in the left, say 3-2-1, Even then, prev would be 1, node will be 2. Current node will have to be greater than prev.
    */
    public boolean isValidBST(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        TreeNode prev = null;

        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                //We pop only when there is no more left.
                if(prev != null && node.val <= prev.val) { //Prev is the last popped value. It should always be lesser than current pop.
                    return false;
                }
                prev = node;
                node = node.right;
            }
        }

        return true;
    }

    /*
    lowerBound changes every time we move to the right. upperBound changes every time we move to the left.
    So whenever we move to the right, the 'root' remains the upperBound. Same goes for left, vice versa.
    The current number should always lie between the ranges.
    */
    public boolean isValidBSTRecursive(TreeNode root) {
        return recursionHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean recursionHelper(TreeNode root, long lowerBound, long upperBound) {
        if(root == null) {
            return true;
        }
        if(root.val <= lowerBound || root.val >= upperBound) {
            return false;
        }
        return recursionHelper(root.left, lowerBound, root.val) && recursionHelper(root.right, root.val, upperBound);
    }
}

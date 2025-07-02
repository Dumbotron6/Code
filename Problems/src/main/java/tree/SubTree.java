package tree;

public class SubTree {
    /*
    https://leetcode.com/problems/subtree-of-another-tree/
     */

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(root == null) { //We have checked every path end reached the end of the root tree.
            return false;
        }
        if(isSameTree(root, subRoot)) {
            return true;
        }
        //If values don't match or recursed subtrees don't match, then left or right should match original subRoot.
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode root, TreeNode subRoot) {
        if(root == null || subRoot == null) {
            if(root == null && subRoot == null) {
                return true;
            }
            return false;
        }

        //If current value matches, then left and right should also match.
        if(root.val == subRoot.val && isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right)) {
            return true;
        }
        return false;
    }
}

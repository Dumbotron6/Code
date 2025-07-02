package tree;

public class PathSum {
    /*
    https://leetcode.com/problems/path-sum/description/
     */

    /*
     This is problem with a very unclear definition. By root, it means it has to start at the top.
     By leaf, it means the very end of the tree. So both left and right need to be null.
     When at a leaf, if the sum matches val, then it means we've achieved target sum.
     Hence why we do targetSum-root.val at recursion. We just subtract at each step instead of adding at each step and lead to targetSum.
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        if(targetSum == root.val && root.left == null && root.right == null) {
            return true;
        }

        return hasPathSum(root.left, targetSum-root.val) || hasPathSum(root.right, targetSum-root.val);
    }
}

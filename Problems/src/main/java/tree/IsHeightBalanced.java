package tree;

public class IsHeightBalanced {
    /*
    https://leetcode.com/problems/balanced-binary-tree/description/
     */

    /*
    Logic is, if the tree balanced, we return the height. If not, ie, the difference in height between left and right
        exceeds 1, we return -1.
     */
    public boolean isBalanced(TreeNode root) {
        if(depthCount(root) != -1)
            return true;
        else
            return false;
    }

    public int depthCount(TreeNode root) {
        if(root == null)
            return 0;

        int left = depthCount(root.left);
        if(left == -1)
            return -1;
        int right = depthCount(root.right);
        if(right == -1)
            return -1;

        if(Math.abs(left-right) > 1)
            return -1;

        return Math.max(left,right) + 1;
    }
}

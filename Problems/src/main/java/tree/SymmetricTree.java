package tree;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    /*
    https://leetcode.com/problems/symmetric-tree/submissions/1373205701/
     */

    public boolean isSymmetric(TreeNode root) {
        return isSymmetricCheck(root.left, root.right);
    }

    /*
    We compare the outer branches and the inner branches. So we compare left.left and right.right - outer branch,
        then we compare tight.left and left.right - inner branch.
     */
    public boolean isSymmetricCheck(TreeNode left, TreeNode right) {
        if(left == null || right == null)
            return left == right;

        boolean leftSym = isSymmetricCheck(left.left, right.right);
        if(!leftSym)
            return false;

        boolean rightSym = isSymmetricCheck(right.left, left.right);
        if(!rightSym)
            return false;

        return left.val == right.val;
    }

    //This uses BFS but has extra space. Same as SameTree.
    public boolean isSymmetricCheckBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode f = queue.poll();
            TreeNode s = queue.poll();
            if (f == null && s == null) {
                continue;
            } else if (f == null || s == null || f.val != s.val) {
                return false;
            }
            queue.add(f.left);
            queue.add(s.left);
            queue.add(f.right);
            queue.add(s.right);
        }
        return true;
    }
}

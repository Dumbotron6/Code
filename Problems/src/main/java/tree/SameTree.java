package tree;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
    /*
    https://leetcode.com/problems/same-tree/description/
     */

    //This uses DFS.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null)
            return p == q;
        if(p.val != q.val)
            return false;

        boolean left = isSameTree(p.left, q.left);
        if(!left)
            return false;
        boolean right = isSameTree(p.right, q.right);
        if(!right)
            return false;
        return true;
    }

    //This uses BFS but has extra space.
    public boolean isSameTreeNonRecur(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
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

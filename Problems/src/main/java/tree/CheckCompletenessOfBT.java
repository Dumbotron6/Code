package tree;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessOfBT {
    /*
    https://leetcode.com/problems/check-completeness-of-a-binary-tree/description/
     */

    /*
    Use BFS/Level order traversal. Since in complete binary trees, only leaf nodes can be null and that too from left to right,
        once we encounter a null node, no node should be encountered again.
    So once we encounter a null node, we flip a boolean flag. If we encounter another node after that, it is not a complete binary tree.
    */
    public boolean isCompleteTree(TreeNode root) {
        boolean missing = false;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean missingTemp = false;
            int i = 0;
            while(i < size) {
                TreeNode node = queue.pop();
                if(node.left != null) {
                    if(missing) {
                        return false;
                    }
                    queue.add(node.left);
                }else {
                    missing = true;
                }

                if(node.right != null) {
                    if(missing) {
                        return false;
                    }
                    queue.add(node.right);
                }else {
                    missing = true;
                }
                i++;
            }
        }

        return true;
    }

    //Below is a solution found in leetcode. Same concept but simplified approach. No real advantage. Just interesting.
    public boolean isCompleteTreeSimplified(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean end = false;  // becomes true when a null child is seen

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                end = true;  // from now on, all nodes must be null
            } else {
                if (end) return false;  // found a non-null node after a null one
                q.add(node.left);
                q.add(node.right);
            }
        }

        return true;
    }
}

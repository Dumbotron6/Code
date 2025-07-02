package tree;

import java.util.LinkedList;

public class DepthOfTree {

    /*
    https://leetcode.com/problems/maximum-depth-of-binary-tree/
    https://leetcode.com/problems/minimum-depth-of-binary-tree/
     */

    //Uses DFS
    public int maxDepthOptimal(TreeNode root) {
        if(root == null)
            return 0;
        int left = maxDepthOptimal(root.left);
        int right = maxDepthOptimal(root.right);

        return 1 + Math.max(left, right);
    }

    //Uses BFS
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for(int i = 0; i < size; i++) {
                if(queue.peek().left != null)
                    queue.add(queue.peek().left);
                if(queue.peek().right != null)
                    queue.add(queue.peek().right);
                queue.pop();
            }
        }
        return depth;
    }

    //Uses BFS. Optimal.
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        int depth = 0;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for(int i = 0; i < size; i++) {
                if(queue.peek().left == null && queue.peek().right == null)
                    return depth;
                if(queue.peek().left != null)
                    queue.add(queue.peek().left);
                if(queue.peek().right != null)
                    queue.add(queue.peek().right);
                queue.pop();
            }
        }
        return depth;
    }

    //Uses DFS
    public int minDepthDFS(TreeNode root) {
        if (root == null) return 0;
        int left = minDepthDFS(root.left);
        int right = minDepthDFS(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }

}

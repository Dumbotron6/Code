package tree;

import java.util.LinkedList;

public class InvertBinaryTree {
    /*
    https://leetcode.com/problems/invert-binary-tree/
     */

    //Use BFS to traverse, while doing so, swap left and right.
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return root;
        }

        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            int i = 0;
            while(i < size) {
                TreeNode node = queue.pop();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if(node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null) {
                    queue.add(node.right);
                }
                i++;
            }
        }

        return root;
    }

    //Uses recursion, not really an improvement as recursion stack space is the same.
    public TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTreeRecursive(root.left);
        invertTreeRecursive(root.right);

        return root;
    }
}

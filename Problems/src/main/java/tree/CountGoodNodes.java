package tree;

public class CountGoodNodes {
    /*
    https://leetcode.com/problems/count-good-nodes-in-binary-tree/
     */

    /*
    We use in-order DFS traversal.
    At each recursion level, we keep track of max till that node and use it to compare the node value.
    */
    int good;
    public int goodNodes(TreeNode root) {
        good = 0;
        recursiveDFS(root, Integer.MIN_VALUE);
        return good;
    }

    public void recursiveDFS(TreeNode node, int max) {
        if(node == null) {
            return;
        }

        if(node.val >= max) {
            good++;
            max = node.val;
        }

        recursiveDFS(node.left, max);
        recursiveDFS(node.right, max);
    }
}

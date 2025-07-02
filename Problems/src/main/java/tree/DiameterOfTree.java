package tree;

public class DiameterOfTree {
    /*
    https://leetcode.com/problems/diameter-of-binary-tree/
     */

    /*
    The idea is, we can find the diameter if, from any node, we know the height(or depth) of the left and right nodes,
        we will get the distance between them.
    So when we do this for every node(by comparing max), we will get the max diameter.
    Consider below
            1
            /\
           2  3
          /   /\
         8   4  5
            /    \
           6      7
     In this node, the height of 6 and 7 are 1. 4 and 5 are 2 (from below, not from the root 1).
     Now when the recursion tracks back to 3, we add up the two heights and the distance is 4.
     But, distance of 2 is 2 and distance of 3 is 3. Adding the two, we get 5. This is the max diameter.
     ie, when we move from 8 to 6 or 7, we get the max diameter.
     If 8 didn't exist, the max diameter would be 4, moving from 6 to 7.
     This approach uses DFS.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[]{0};
        findDia(root, diameter);
        return diameter[0];
    }

    public int findDia(TreeNode root, int[] max) {
        if(root == null)
            return 0;

        int left = findDia(root.left, max);
        int right = findDia(root.right, max);

        max[0] = Math.max(max[0], left+right);

        return 1 + Math.max(left, right);
    }
}

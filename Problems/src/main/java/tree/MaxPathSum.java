package tree;

public class MaxPathSum {
    /*
    https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
     */

    /*
    The logic is the same as DiameterOfTree. At each node traversing backward(via recursion), we find the maximum sum.
     */
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        findMax(root);
        return maxSum;
    }

    public int findMax(TreeNode root) {
        if(root == null)
            return 0;
        /*
        The reason for doing Math.max(0,root.left) is, left or right sum might give us negative numbers.
        In such cases, adding to the node's value would only give us a sum below the node's value.
            ie, the node's value will always be higher than if we don't add it with a negative.
        Below is the example.
            2
            /
           -2
           /
          1
         The sum for 1 would be 1, -1 would be -1, and 2 would be 0 which is lower than the node's value 2.
         If instead of 2,-1,1 we had 3.-1,4 then it's fine as the sum at any point wouldn't be -ve.
         */
        int left = Math.max(0, findMax(root.left));
        int right = Math.max(0, findMax(root.right));

        maxSum = Math.max(maxSum, left+right+root.val);

        return root.val + Math.max(left, right);
    }
}

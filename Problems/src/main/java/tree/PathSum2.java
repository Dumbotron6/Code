package tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {
    /*
    https://leetcode.com/problems/path-sum-ii/
     */

    //Same as path sum. Only this time, we carry the node values in a list and the sum till now.
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if(root == null) {
            return result;
        }

        dfs(result, new ArrayList<Integer>(), 0, targetSum, root);
        return result;
    }

    public void dfs(List<List<Integer>> result, List<Integer> current, int sum, int targetSum, TreeNode node) {
        int currSum = sum + node.val;
        current.add(node.val);

        if(node.left == null && node.right == null) {
            if(currSum == targetSum) {
                result.add(new ArrayList<Integer>(current));
            }
            current.removeLast();
            return;
        }

        if(node.left != null) {
            dfs(result, current, currSum, targetSum, node.left);
        }
        if(node.right != null) {
            dfs(result, current, currSum, targetSum, node.right);
        }
        current.removeLast();
    }
}

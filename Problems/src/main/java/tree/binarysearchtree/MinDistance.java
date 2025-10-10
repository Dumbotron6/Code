package tree.binarysearchtree;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MinDistance {
    /*
    https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/
     */

    //In-order traversal gives us BST in sorted form.
    public int minDiffInBST(TreeNode root) {
        List<Integer> allVals = new ArrayList<Integer>();
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        int minVal = Integer.MAX_VALUE;

        while(!stack.isEmpty() || node != null) {
            if(node != null) {
                stack.push(node);
                node = node.right;
            }else {
                node = stack.pop();
                allVals.add(node.val);
                node = node.left;
            }
        }

        for(int i = 0; i < allVals.size()-1; i++) {
            minVal = Math.min(allVals.get(i)-allVals.get(i+1), minVal);
        }

        return minVal;
    }
}

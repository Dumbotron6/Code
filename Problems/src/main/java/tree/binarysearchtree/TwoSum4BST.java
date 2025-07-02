package tree.binarysearchtree;

import tree.TreeNode;

import java.util.LinkedList;

public class TwoSum4BST {
    /*
    https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
     */

    /*
    The naive solution is to get all elements of BST and store them in a list. Then do a regular two sum on the list using two pointers.
    This just builds on that. We still sum the smallest and greatest values and keep shrinking till we no longer can.
    We apply BSTIterator to get the smallest node each time. We apply it in reverse to get the greatest node each time.
    So we have two stacks.
    */
    public boolean findTarget(TreeNode root, int k) {
        LinkedList<TreeNode> stackSmallest = new LinkedList<TreeNode>();
        LinkedList<TreeNode> stackGreatest = new LinkedList<TreeNode>();

        putSmallest(root, stackSmallest);
        putGreatest(root, stackGreatest);

        TreeNode smallest = getSmallest(stackSmallest);
        TreeNode greatest = getGreatest(stackGreatest);

        while(smallest.val < greatest.val) {
            int sum = smallest.val + greatest.val;
            if(sum < k) {
                smallest = getSmallest(stackSmallest);
            }else if(sum > k) {
                greatest = getGreatest(stackGreatest);
            }else {
                return true;
            }
        }

        return false;
    }

    public void putSmallest(TreeNode root, LinkedList<TreeNode> stackSmallest) {
        while(root != null) {
            stackSmallest.push(root);
            root = root.left;
        }
    }

    public void putGreatest(TreeNode root, LinkedList<TreeNode> stackGreatest) {
        while(root != null) {
            stackGreatest.push(root);
            root = root.right;
        }
    }

    public TreeNode getSmallest(LinkedList<TreeNode> stackSmallest) {
        TreeNode retNode = stackSmallest.pop();
        if(retNode.right != null) {
            putSmallest(retNode.right, stackSmallest);
        }
        return retNode;
    }

    public TreeNode getGreatest(LinkedList<TreeNode> stackGreatest) {
        TreeNode retNode = stackGreatest.pop();
        if(retNode.left != null) {
            putGreatest(retNode.left, stackGreatest);
        }
        return retNode;
    }
}

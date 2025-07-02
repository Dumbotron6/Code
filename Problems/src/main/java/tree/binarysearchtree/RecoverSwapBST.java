package tree.binarysearchtree;

import tree.TreeNode;

import java.util.LinkedList;

public class RecoverSwapBST {
    /*
    https://leetcode.com/problems/recover-binary-search-tree/description/
     */

    /*
    We interate through the tree using inorder traversal and spot the places where the values are incorrect.
    In in order, traversal, the result we get would strictly be in ascending order.
    There will be exactly two places where the values are incorrect. But there are two cases for it.
    Take [3,25,7,8,15,5] and [3,8,7,15,29,25] in order values while traversing.
    In the first case, 25 and 5 are swapped. In the second case, 8 and 7 are swapped.
    When we find the first incorrect place, we assume that and it's prev node are incorrect but we keep looking anyway.
    If we find a second incorrect place, then it means only the first one we found is out of place and the adjacent one is correct.
    So we swap the newly found one with the first incorrect one.
    */
    public void recoverTree(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode firstIncorrect = null;
        TreeNode secondIncorrect = null;
        TreeNode prev = null;

        while(!stack.isEmpty() || root != null) {
            if(root != null) {
                stack.push(root);
                root = root.left;
            }else {
                root = stack.pop();
                if(prev != null && root.val < prev.val) {
                    if(firstIncorrect == null) {
                        firstIncorrect = prev;
                    }
                    secondIncorrect = root;
                }
                prev = root;
                root = root.right;
            }
        }

        int temp = firstIncorrect.val;
        firstIncorrect.val = secondIncorrect.val;
        secondIncorrect.val = temp;
    }


    //Same solution but using recursion.
    TreeNode firstIncorrect = null;
    TreeNode secondIncorrect = null;
    TreeNode prev = null;
    public void recoverTreeRecursive(TreeNode root) {
        traverse(root);

        int temp = firstIncorrect.val;
        firstIncorrect.val = secondIncorrect.val;
        secondIncorrect.val = temp;
    }

    public void traverse(TreeNode root) {
        if(root == null) {
            return;
        }

        traverse(root.left);

        if(prev != null && root.val < prev.val) {
            if(firstIncorrect == null) {
                firstIncorrect = prev;
            }
            secondIncorrect = root;
        }

        prev = root;
        traverse(root.right);
    }
}

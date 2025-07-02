package tree.binarysearchtree;

import tree.TreeNode;

import java.util.LinkedList;

public class BSTIterator {
    /*
    https://leetcode.com/problems/binary-search-tree-iterator/description/
     */

    /*
    For this, we could do an inorder traversal(left,node,right) and store it in a list and then return the result.
    That would give space complexity of O(N) and time complexity of O(1) for next() .
    Instead, we store nodes in a stack and at most, we'd store O(H) where H is the height of the tree, at any given time.
    The time complexity for next() would be O(H) for each call at max but O(1) on average.
    */

    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();

    public BSTIterator(TreeNode root) {
        while(root != null) { //At all times, the top of the stack contains the least value node.
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode retNode = stack.pop(); //We pop the least element and return it.
        TreeNode iter = retNode.right; //We check the right of the popped element to check if anything is greater.
        while(iter != null) { //We iterate till we reach the least of the greater elements and have that become the top of the stack.
            stack.push(iter);
            iter = iter.left;
        }

        return retNode.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

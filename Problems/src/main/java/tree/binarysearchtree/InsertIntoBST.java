package tree.binarysearchtree;

import tree.TreeNode;

public class InsertIntoBST {
    /*
    https://leetcode.com/problems/insert-into-a-binary-search-tree/
     */

    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insert(root, val);
    }

    /*
    When we inset to a BST, the new value will end up as a leaf node. We just have to find where.
    We will know we have reached when we have reached the leftmost or the rightmost of an existing leaf node.
    */
    public TreeNode insert(TreeNode root, int val) {
        if(root == null) { //Reached the end where val needs to be inserted.
            return new TreeNode(val);
        }

        if(root.val > val) { //Current value is greater, so go left.
            root.left = insert(root.left, val);
        }else if(root.val < val) { //Current value is lesser, so go right.
            root.right = insert(root.right, val);
        }
        return root;
    }
}

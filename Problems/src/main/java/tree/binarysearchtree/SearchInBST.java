package tree.binarysearchtree;

import tree.TreeNode;

public class SearchInBST {
    /*
    https://leetcode.com/problems/search-in-a-binary-search-tree/
     */

    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) {
            return root;
        }

        if(val < root.val) { //If value is less, look to the left tree.
            return searchBST(root.left, val);
        }else if(val > root.val) { //If value is greater, look to the right tree.
            return searchBST(root.right, val);
        }else {
            return root;
        }
    }
}

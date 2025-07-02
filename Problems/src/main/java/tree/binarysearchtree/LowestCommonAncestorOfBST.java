package tree.binarysearchtree;

import tree.TreeNode;

public class LowestCommonAncestorOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode smaller;
        TreeNode greater;
        if(p.val > q.val) {
            greater = p;
            smaller = q;
        }else {
            greater = q;
            smaller = p;
        }

        return lcaHelper(root, smaller, greater);
    }

    //The first node that has value between the smaller and greater node is the answer.
    public TreeNode lcaHelper(TreeNode root, TreeNode smaller, TreeNode greater) {
        if(root == null) {
            return null;
        }

        if(root.val >= smaller.val && root.val <= greater.val) {
            return root;
        }

        TreeNode left = lcaHelper(root.left, smaller, greater);
        TreeNode right = lcaHelper(root.right, smaller, greater);

        if(left != null) {
            return left;
        }

        return right;
    }

    public TreeNode lowestCommonAncestorAlt(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) {
            return null;
        }

        if(root.val < p.val && root.val < q.val) { //Both values greater.
            return lowestCommonAncestorAlt(root.right, p, q);
        }else if(root.val > p.val && root.val > q.val) { //Both values lesser.
            return lowestCommonAncestorAlt(root.left, p, q);
        }

        return root;
    }
}

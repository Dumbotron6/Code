package tree;

public class LowestCommonAncestor {
    /*
    https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    /*
    We are guaranteed to find p and q. So there are three possibilities.
    1. The current node is the LCA, in which case p and q will exist in left and right of current node.
    2. q exists below p.
    3. p exists below q.
    left and right will both be not null only in the first possibility.
    Since we are guaranteed to find LCA, we do not need to check down once we found p or q.
    We only have to check the other paths, ie, paths above where p or q were found.
    Since those paths are where q(if p was found) could possibly be found if not below p.
    If those paths return false, we are sure to know that p is the LCA.
    */
    public TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p) {
            return root;
        }
        if(root == q) {
            return root;
        }

        TreeNode left = null, right = null;
        if(root.left != null) {
            left = dfs(root.left, p, q);
        }

        if(root.right != null) {
            right = dfs(root.right, p, q);
        }

        if(left != null && right != null) {
            return root;
        }else if(left != null) {
            return left;
        }

        return right;
    }
}

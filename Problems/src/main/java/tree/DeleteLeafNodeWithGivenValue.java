package tree;

public class DeleteLeafNodeWithGivenValue {
    /*
    https://leetcode.com/problems/delete-leaves-with-a-given-value/description/
     */

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return deleteLeaf(root, target);
    }

    public TreeNode deleteLeaf(TreeNode node, int target) {
        if(node == null) {
            return null;
        }

        /*
        Recursively check left and right first. This node may not be a leaf node yet, but going down might result in
            the leaf nodes being deleted and this node ending up as the leaf node.
        */
        node.left = deleteLeaf(node.left, target);
        node.right = deleteLeaf(node.right, target);

        //If leaf node and value matches, delete it, so return null.
        if(node.left == null && node.right == null && node.val == target) {
            return null;
        }

        return node;
    }
}

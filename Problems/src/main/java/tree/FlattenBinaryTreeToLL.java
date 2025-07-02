package tree;

public class FlattenBinaryTreeToLL {
    /*
    https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
     */

    public void flatten(TreeNode root) {
        flat(root);
    }

    public void flat(TreeNode node) {
        if(node == null) {
            return;
        }

        TreeNode tempRight = node.right;
        TreeNode tempLeft = node.left;

        flat(tempLeft); //Left will be flattened to have only right nodes in them.
        flat(tempRight); //Right will be flattened to have only right nodes in them.

        node.left = null; //Make left node null.
        node.right = tempLeft; //Assign left node to right. We have already stored existing right in tempRight.

        /*
        Since all left and right nodes are flattened recursively, we can safely say everything to the node's right(tempLeft)
            at this point will have only right nodes. So we can safely use while loop to find the end.
        Then we connect this end with previous right node, thus combining flattened left and right nodes.
        */
        TreeNode rightEnd = node;
        while(rightEnd.right != null) {
            rightEnd = rightEnd.right;
        }

        rightEnd.right = tempRight;
    }
    /*
        1
        /\
       2  4
       \
        3
    Take above example. At the top level of recursion, node.left is 2 and node.right is 4. We recursively flatten and combine them.
    We store 4 in tempRight, make 2 as 1.right, loop till we find it's extreme end which is 3, then assign 3.right as 4.
    */
}

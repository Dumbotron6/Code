package tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeFromPreorderAndInorder {
    /*
    https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
     */

    int idx = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<Integer, Integer>();

        for(int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return helper(preorder, inOrderMap, 0, preorder.length-1);
    }

    /*
    The idea is, we know preorder[0] is the root. Now when we find the root in inOrder,
        then everything to its left is the left of the tree
        and everything to its right is the right of the tree.
    In the example preorder = [3,9,20,15,7], inorder = [9,3,15,20,7], in inorder, everything to left(index 0 to 0) forms the left tree
        and everything to right(index 2 to 4) forms the right tree.
    We apply this recursively, iterating over everything in preorder, finding the corresponding head in inorder,
        increment idx, assume preorder[idx] is the left head. Then assume preorder[idx] is right head and do recursion.
    We look for left first because that is how preorder is structured, root-left-right.
    If there did exist left or right heads, then idx would have incremented inside the recursive call. If not,
        there is no left or right head(no more elements in inorder), we'd run into (left > right) or (idx >= preOrder.length).
    */
    public TreeNode helper(int[] preOrder, Map<Integer, Integer> inOrderMap, int left, int right) {
        if(left > right) {
            return null;
        }
        if(idx >= preOrder.length) {
            return null;
        }

        TreeNode node = new TreeNode(preOrder[idx]);
        idx++;
        int inHead = inOrderMap.get(node.val); //Position in inorder.
        int newRight = inHead-1; //For left subtree. Everything to left in inorder.
        int newLeft = inHead+1; //For right subtree. Everything to right in inorder.

        node.left = helper(preOrder, inOrderMap, left, newRight);
        node.right = helper(preOrder, inOrderMap, newLeft, right);

        return node;
    }
}

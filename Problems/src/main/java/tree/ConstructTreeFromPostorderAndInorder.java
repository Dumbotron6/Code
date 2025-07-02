package tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeFromPostorderAndInorder {
    /*
    https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
     */

    int idx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderMap = new HashMap<Integer, Integer>();
        idx = inorder.length-1;

        for(int i = 0; i <= idx; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return helper(postorder, inOrderMap, 0, idx);
    }

    /*
    Exactly the same concept as ConstructTreeFromPreorderAndInorder.
    Only instead of Preorder, we have Postorder. So the root will be at postorder.length-1 instead of 0.
    When we find root in inorder, everything to left will be left subtree and
        everything to right will be right subtree.
    The difference, is we look for right subtree here first instead of left subtree. Because that is how postorder is structured,
        left-right-root. Now in reverse, that's root-right-left. Preorder is root-left-right, so we looked for left first there.
    */
    public TreeNode helper(int[] postOrder, Map<Integer, Integer> inOrderMap, int left, int right) {
        if(right < left) {
            return null;
        }
        if(idx < 0) {
            return null;
        }

        TreeNode node = new TreeNode(postOrder[idx]);
        idx--;
        int inOrderHead = inOrderMap.get(node.val);
        int newRight = inOrderHead-1;
        int newLeft = inOrderHead+1;

        node.right = helper(postOrder, inOrderMap, newLeft, right);
        node.left = helper(postOrder, inOrderMap, left, newRight);
        return node;
    }
}

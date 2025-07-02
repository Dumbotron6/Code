package tree.binarysearchtree;

import tree.TreeNode;

import java.util.LinkedList;

public class ConstructBSTFromPreorder {
    /*
    https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
     */

    /*
    For BST, every element on should be node should be lesser and every node on right should be greater.
    In preorder, the order is root, left, right. So the root is always at index 0, which we have to return.
    Now it's only a matter of picking what goes left and what goes right.
    That is the only property of preorder that we use for this approach.
    As long as we have a lesser node, we keep insert to left.
    When we find a greater node, we keep popping till we find the topmost element that was lesser than the greater node,
        insert to its right.
    Optimal solution is below.
    */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = new TreeNode(preorder[0]);
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);

        for(int i = 1; i < preorder.length; i++) {
            TreeNode newNode = new TreeNode(preorder[i]);

            TreeNode currNode = stack.peek();
            if(newNode.val < currNode.val) { //Keep inserting at left as long as new element is lesser than top of stack ie, recently inserted node.
                currNode.left = newNode;
            }else {
                currNode = stack.pop();
                while(!stack.isEmpty() && stack.peek().val < newNode.val) { //Pop till we find topmost element that is lesser.
                    currNode = stack.pop();
                }
                currNode.right = newNode;
            }
            stack.push(newNode);
        }

        return root;
    }

    /*
    For BST, every element on should be node should be lesser and every node on right should be greater.
    In preorder, the order is root, left, right. So the root is always at index 0, which we have to return.
    Now it's only a matter of picking what goes left and what goes right.
    Take example [8,5,1,7,10,12] and root 8, we need to find all elements that go to it's right. What does that mean? 5 to 1 are lesser,
        ie, indexes 1 to 2 are lesser. Index from 3 onwards is greater. We can be sure of that because the preorder that we are given
        is a BST. We just have to construct it.
    Now recursively, we check what goes left and right inside each section that we find.
    That is, for 8, we found the index till which we should go left and the index after which we should go right.
    Now take the left section and recursively check what should go left and right. Same for right section.
    */
    public TreeNode bstFromPreorderOptimal(int[] preorder) {
        return recursionHelper(preorder, 0, preorder.length-1);
    }

    public TreeNode recursionHelper(int[] preorder, int pos, int end) {
        if(pos > end) { //Either we have reached the end, or there are no lesser or greater elements to the right of root.
            return null;
        }

        TreeNode node = new TreeNode(preorder[pos]);
        int i = pos;

        while(i <= end) { //Find first greater element.
            if(preorder[i] > preorder[pos]) {
                break;
            }
            i++;
        }

        node.left = recursionHelper(preorder, pos+1, i-1); //Everything from pos+1 to i-1 should be lesser, so we go left.
        node.right = recursionHelper(preorder, i, end); //Everything from i to end should be greater, so we go right.

        return node;
    }
}

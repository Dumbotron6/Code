package tree.binarysearchtree;

import tree.TreeNode;

public class DeleteNodeInBST {

    /*
    This solution will give a rather unbalanced BST.
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        return searchAndReplace(root, key);
    }

    public TreeNode searchAndReplace(TreeNode root, int key) {
        if(root == null) {
            return root;
        }

        if(root.val > key) { //If key is lesser, go left, assign the result to root.left.
            root.left = searchAndReplace(root.left, key);
        }else if(root.val < key) { //If key is greater, go right, assign the result to root.right.
            root.right = searchAndReplace(root.right, key);
        }else if(root.val == key) { //If key is found, delete.
            if(root.right != null) { //If greater child is found.
                TreeNode maxRight = root.right;
                TreeNode returnNode = maxRight;
                while(maxRight.left != null) { //Go till least of greater child is found.
                    maxRight = maxRight.left;
                }
                maxRight.left = root.left; //To least of greater child, assign the root's lesser child.
                return returnNode;
            }else { //If only lesser child exists.
                return root.left;
            }
        }
        /*
        Why have the final if? Won't it always be true? Yes but after root.left or root.right assignment,
            we need to return root. If there is no final if condition, we'd end up returning returnNode or root.left
            always.
        Alternatively, we can immediately return root like
        if(root.val > key) {
            root.left = searchAndReplace(root.left, key);
            return root.
        }
         */
        return root;
    }

    /*
                5
                /\
               3  6
              /\   \
             2  4   7
    Take above example, 3 needs to be deleted. Once 3 is deleted, then next greater element needs to be assigned to 5.left.
    As we know everything to left of 3 is smaller and everything to right of 3 is greater, we know that immediate 3.left will
        be smaller than all of 3.right. So the least of 3.right needs to be assigned as parent of 2.
    3.right needs to become 5.left now that 3 is deleted.
    Basically, we are reassigning everything below 3 and then assigning it to 5.
    */

    //This solution will give a balanced BST. Because we are taking the least greater and making it the current node.
    public TreeNode deleteNodeAlt(TreeNode root, int key) {
        if(root==null){
            return null;
        }

        //searching
        if(key<root.val){
            root.left = deleteNodeAlt(root.left,key);
        }
        else if(key>root.val){
            root.right = deleteNodeAlt(root.right,key);
        }
        else{
            //with no children
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }

            //if two children
            TreeNode successor = findmin(root.right);
            root.val = successor.val;//replace
            root.right = deleteNodeAlt(root.right,successor.val);
        }
        return root;
    }
    private TreeNode findmin(TreeNode root){
        while(root.left!=null){
            root = root.left;
        }
        return root;
    }
}

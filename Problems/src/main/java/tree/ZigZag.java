package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ZigZag {
    /*
    https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
     */

    //We use BFS to traverse the nodes and depending on the level, we add the node value to the beginning or end of array.
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> finalList = new ArrayList<List<Integer>>();
        if(root == null)
            return finalList;
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        int level = 1;
        queue.add(root);

        while(!queue.isEmpty()) {
            level++;
            int size = queue.size();
            List<Integer> levelList = new ArrayList<Integer>();
            for(int i = 0; i < size; i++) {
                TreeNode temp = queue.pop();
                if(temp.left != null)
                    queue.add(temp.left);
                if(temp.right != null)
                    queue.add(temp.right);
                if(level%2 == 0)
                    levelList.add(temp.val);
                else
                    levelList.addFirst(temp.val);
            }
            finalList.add(levelList);
        }

        return finalList;
    }
}

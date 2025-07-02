package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;

/*
https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 */
public class TopView {
    static ArrayList<Integer> topView(TreeNode root) {
        ArrayList<Integer> finalList = new ArrayList<Integer>();
        if(root == null)
            return finalList;

        class Pair {
            TreeNode node;
            int col;
            Pair(TreeNode node, int col) {
                this.node = node;
                this.col = col;
            }
        }

        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
        LinkedList<Pair> queue = new LinkedList<Pair>();
        queue.push(new Pair(root, 0));
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Pair temp = queue.pop();
                if(temp.node.left != null)
                    queue.add(new Pair(temp.node.left, temp.col-1));
                if(temp.node.right != null)
                    queue.add(new Pair(temp.node.right, temp.col+1));

                if(!tm.containsKey(temp.col))
                    tm.put(temp.col, temp.node.val);
            }
        }

        for(int val : tm.values()) {
            finalList.add(val);
        }
        return finalList;
    }
}

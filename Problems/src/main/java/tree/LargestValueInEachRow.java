package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LargestValueInEachRow {
    /*
    https://leetcode.com/problems/find-largest-value-in-each-tree-row/
     */

    //Use BSF to traverse. At each row, find and add the max to a list.
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> largest = new ArrayList<Integer>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        if(root != null)
            queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for(int i = 0; i < size; i++) {
                if(queue.peek().left != null) {
                    queue.add(queue.peek().left);
                }
                if(queue.peek().right != null) {
                    queue.add(queue.peek().right);
                }
                max = Math.max(max, queue.pop().val);
            }
            largest.add(max);
        }
        return largest;
    }
}

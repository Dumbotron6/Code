package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightView {
    /*
    https://leetcode.com/problems/binary-tree-right-side-view/description/
     */

    /*
    We use DFS to traverse. Reason for if(finalList.size() == level) is, the first element encountered on that level
        would be inserted after which finalList.size() would increase. For example, if level 3 is encountered first on
        the right, it will be inserted.
    Lets say right has 5 elements and left has 7. Since right is traversed first, 5 elements of right would be inserted
        into the list. Now when left is traversed, 1 through 5 will not be inserted because size of the list has
        already exceeded that. But 6 and 7 will be inserted from the left because the size of the list was only 5.
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> finalList = new ArrayList<Integer>();
        rightView(root, 0, finalList);
        return finalList;
    }

    public void rightView(TreeNode node, int level, List<Integer> finalList) {
        if(node != null) {
            if(finalList.size() == level)
                finalList.add(node.val);
            rightView(node.right, level+1, finalList);
            rightView(node.left, level+1, finalList);
        }
    }

    //BFS but uses extra[O(N)] space. In this level, we insert the last element we encounter in each level into the list.
    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode curNode = q.poll(); // for left assign if curNode != null
                if (i == n - 1) {
                    res.add(curNode.val);
                }
                if (curNode.left != null) {
                    q.offer(curNode.left);
                }
                if (curNode.right != null) {
                    q.offer(curNode.right);
                }
            }
        }
        return res;
    }
}

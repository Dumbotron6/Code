package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    /*
    https://leetcode.com/problems/binary-tree-paths/description/
     */

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        List<Integer> traverseNodes = new ArrayList<Integer>();

        getPath(root, result, traverseNodes);

        return result;
    }

    public void getPath(TreeNode node, List<String> result, List<Integer> traverseNodes) {
        traverseNodes.add(node.val);
        if(node.left == null && node.right == null) {
            buildPath(result, traverseNodes);
            traverseNodes.removeLast();
            return;
        }

        if(node.left != null) {
            getPath(node.left, result, traverseNodes);
        }
        if(node.right != null) {
            getPath(node.right, result, traverseNodes);
        }
        traverseNodes.removeLast();
    }

    public void buildPath(List<String> result, List<Integer> traverseNodes) {
        int len = traverseNodes.size()-1;
        StringBuilder path = new StringBuilder();
        for(int i = 0; i < len; i++) {
            path.append(traverseNodes.get(i)).append("->");
        }
        path.append(traverseNodes.getLast());
        result.add(path.toString());
    }
}

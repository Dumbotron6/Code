package tree;

public class CountCompleteTreeNodes {
    /*
    https://leetcode.com/problems/count-complete-tree-nodes/description/
     */
    /*
    Brute force would be to do a BFS or DFS to count every element and that would result in O(N) time.
    */
    public int countNodes(TreeNode root) {
        return dfs(root);
    }

    /*
    Since this is a complete binary tree, we can optimize it to calculate count using the height.
    How does this avoid O(N)? Notice at each node, we go left and go right to get the height.
    If heights match, then the count would be 2^h-1. If the heights don't match, then the left or right of the node is short.
    For when height is equal, the traversal would be like below.
    1
    /\
    2 3
    /  \
    4   7
    Notice that we didn't have to traverse to 5 and 6 because if right(7) exists, then it is a perfect tree(all nodes present).
    We only traverse further down to both left and right when there is a height difference.
    The height of a tree at any instance will be logN. At every node, we might count height again if left and right don't match.
    So time complexity is O((logN)^2).
    */
    public int dfs(TreeNode node) {
        if(node == null) {
            return 0;
        }

        int leftH = 0;
        int rightH = 0;
        TreeNode lNode = node;
        TreeNode rNode = node;
        while(lNode != null) {
            leftH++;
            lNode = lNode.left;
        }
        while(rNode != null) {
            rightH++;
            rNode = rNode.right;
        }

        if(leftH == rightH) {
            return (int)Math.pow(2,leftH)-1;
        }

        return 1 + dfs(node.left) + dfs(node.right);
    }
}

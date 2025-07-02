package tree;

import java.util.ArrayList;
import java.util.List;

public class MaximumWidthOfTree {
    /*
    https://leetcode.com/problems/maximum-width-of-binary-tree/
     */

    public int widthOfBinaryTree(TreeNode root) {
        List<long[]> depthLen = new ArrayList<long[]>();
        int pos = 0;
        bfs(root, depthLen, 1, 0);

        long maxDiff = 0;
        for(long[] minMax : depthLen) {
            maxDiff = Math.max(maxDiff, minMax[1]-minMax[0]+1);
        }

        return (int)maxDiff;
    }

    /*
    We do a BFS. Going down each level, we mark positions.
    Level 1 would have 1, level 2 would have 2,3.
    Level 3 would have 4,5,6,7, level 4 would have 8,9,10,11,12,13,14,15.
    We store the min and max positions of each level in depthLen.
    The positions for left and right being 2*pos and 2*pos+1 is purely math.
    Draw the diagram and note down the positions for clarity.
    */
    public void bfs(TreeNode root, List<long[]> depthLen, long pos, int depth) {
        if(root == null) {
            return;
        }

        if(depth == depthLen.size()) {
            depthLen.add(new long[]{pos,pos});
        }
        if(pos < depthLen.get(depth)[0]) {
            depthLen.get(depth)[0] = pos;
        }
        if(pos > depthLen.get(depth)[1]) {
            depthLen.get(depth)[1] = pos;
        }
        bfs(root.left, depthLen, 2*pos, depth+1);
        bfs(root.right, depthLen, 2*pos+1, depth+1);
    }

    /*
    Builds on the previously submitted recursion solution.
    In the below BFS code, we store the node, the position and the depth in a Pair. That pair is stored in a queue.
    pair.getValue()[0] is the position and pair.getValue()[1] is the depth.
    if(depth > prevDepth) is how we identify if we have entered a new depth. This is the reason we store depth in the pair.
    When we enter a new depth, due to BFS, the first element we encounter will have the minimum position.
    Every element at that level going forward will be used to calculate maxDiff.
    Why doesn't this run into int limit exceeded like the previous solution?
    Because when an int limit exceeds, it loops around to the other side. 2,147,483,647+1 will give -2,147,483,648.
    This led to incorrect results when using int in previous solution.
    Here it's not a problem because we are using current-min to calculate the difference, not max-min like previous solution.
    */
    //This uses import javafx.util.Pair;
//    public int widthOfBinaryTreeAlt(TreeNode root) {
//        LinkedList<Pair<TreeNode, int[]>> queue  = new LinkedList<Pair<TreeNode, int[]>>();
//        int maxDiff = 0;
//        int min = 0;
//        int prevDepth = 1;
//        queue.add(new Pair<>(root, new int[]{0,1}));
//
//        while(!queue.isEmpty()) {
//            Pair<TreeNode, int[]> pair = queue.pop();
//            TreeNode node = pair.getKey();
//            int depth = pair.getValue()[1];
//
//            if(depth > prevDepth) {
//                prevDepth = depth;
//                min = pair.getValue()[0];
//            }
//            maxDiff = Math.max(maxDiff, pair.getValue()[0]-min+1);
//            if(node.left != null) {
//                queue.add(new Pair<>(node.left, new int[]{2*pair.getValue()[0], depth+1}));
//            }
//            if(node.right != null) {
//                queue.add(new Pair<>(node.right, new int[]{2*pair.getValue()[0]+1, depth+1}));
//            }
//        }
//
//        return maxDiff;
//    }
}

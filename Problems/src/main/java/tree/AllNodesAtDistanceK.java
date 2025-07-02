package tree;

import java.util.*;

public class AllNodesAtDistanceK {
    /*
    https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
     */

    Set<TreeNode> visited;
    List<Integer> kDistNodes;
    Map<TreeNode, TreeNode> parentMap;
    int kTarget;
    /*
    In a tree, we can traverse down, in which case calculating distance is easy.
    However, we can't traverse up so how do we get distance for nodes preceding target?
    We use BFS to iterate all nodes and store their parents in a map.
    */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        parentMap = new HashMap<TreeNode, TreeNode>();
        parentMap.put(root, null);
        queue.push(root);

        while(!queue.isEmpty()) {
            TreeNode curr = queue.pop();
            if(curr.left != null) {
                queue.add(curr.left);
                parentMap.put(curr.left, curr);
            }
            if(curr.right != null) {
                queue.add(curr.right);
                parentMap.put(curr.right, curr);
            }
        }
        visited = new HashSet<TreeNode>();
        kDistNodes = new ArrayList<Integer>();
        kTarget = k;

        dfs(target, 0);
        return kDistNodes;
    }

    /*
    We then perform dfs, traversing down as usual and traversing up using the parentMap we formed above.
    We maintain a set of visited nodes so that we don't revisit same elements. For example, in [3,5,1,6,2,0,8,null,null,7,4] and target 5,
        we traverse up (and down) from 5, but because of dfs call like below, 3.left will take us back to 5 again.
    */
    public void dfs(TreeNode curr, int dist) {
        if(curr == null || visited.contains(curr) || dist > kTarget) {
            return;
        }
        visited.add(curr);
        if(dist == kTarget) {
            kDistNodes.add(curr.val);
            return;
        }

        dfs(parentMap.get(curr), dist+1);
        dfs(curr.left, dist+1);
        dfs(curr.right, dist+1);
    }
}

package graph;

import java.util.ArrayList;
import java.util.List;

public class EventualSafeNodes {
    /*
    https://leetcode.com/problems/find-eventual-safe-states/
     */

    Boolean[] isCycle;
    boolean[] visited;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        //Unless there is a cycle, every node will eventually lead to a terminal node.
        //So we need to identify all cycle nodes. Others will all be safe nodes.
        isCycle = new Boolean[graph.length];
        visited = new boolean[graph.length];
        List<Integer> safe = new ArrayList<Integer>();

        for(int i = 0; i < graph.length; i++) {
            if(!dfsHasCycle(graph, i)) { //Add to safe if cycle not detected.
                safe.add(i);
            }
        }

        return safe;
    }

    public boolean dfsHasCycle(int[][] graph, int i) {
        if(isCycle[i] != null) { //Already checked this node to see if cycle present or not.
            return isCycle[i];
        }
        if(visited[i]) { //Visited this node during current recursion, meaning cycle is present.
            return true;
        }

        boolean hasCycle = false;

        visited[i] = true;
        for(int j : graph[i]) {
            if(dfsHasCycle(graph, j)) { //Cycle detected in recursion.
                hasCycle = true;
                break;
            }
        }
        visited[i] = false;

        isCycle[i] = hasCycle;

        return isCycle[i]; //Will return true if cycle is present.
    }
}

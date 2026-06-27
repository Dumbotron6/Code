package graph;

import java.util.*;

public class MinimumHeightTrees {
    /*
    https://leetcode.com/problems/minimum-height-trees/
     */

    /*
    Revise. Hard problem to understand.
    We keep finding leaf nodes- nodes with only one edge. Those can never be nodes with min height.
    We loop and trim the leaf nodes, moving inward each time. By the time we reach a count of 2 or 1 in the leaf node list,
        it means we have reached the middle. This middle point is where we can traverse other nodes that would lead to min height.
    Take 1-2-3-4-5-6 for example. We eliminate 1,6 in the first loop. We can safely do that beacuse if we picked 1 or 6 as the root,
        it would have given us a height of 6. In the next loop, we eliminate 2 and 5 as that would have given us a height of 4.
    Now we are left with 3 and 4. Taking either as root would give us height of 3 which is the minimum.
    At most, there can only be 2 nodes with minHeight depending on whether n is odd or even.
    */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();

        for(int[] edge : edges) {
            if(!adjList.containsKey(edge[0])) {
                adjList.put(edge[0], new ArrayList<Integer>());
            }
            if(!adjList.containsKey(edge[1])) {
                adjList.put(edge[1], new ArrayList<Integer>());
            }
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        LinkedList<Integer> leaves = new LinkedList<Integer>();
        Map<Integer, Integer> edgeCnt = new HashMap<Integer, Integer>();

        if(adjList.isEmpty()) { //Edge case where there is only one node.
            leaves.add(0);
            return leaves;
        }

        for(int node : adjList.keySet()) {
            int neiSize = adjList.get(node).size();
            if(neiSize == 1) { //If there's only one neighbour, it's a leaf node.
                leaves.add(node);
            }
            edgeCnt.put(node, neiSize); //Keep count of number of neighbours of this node
        }

        while(!leaves.isEmpty()) {
            if(n <= 2) {
                return leaves;
            }

            int length = leaves.size();
            for(int i = 0; i < length; i++) {
                int node = leaves.pop();
                n--;                    //We are eliminating this leaf node.
                for(int nei : adjList.get(node)) {
                    edgeCnt.put(nei, edgeCnt.get(nei)-1); //Since we eliminated this node, we remove it from it's respective neighbours and thus reduce count.
                    if(edgeCnt.get(nei) == 1) { //Add to leaf list, if this neighbour becomes a leaf.
                        leaves.add(nei);
                    }
                }
            }
        }

        return leaves;
    }

    //Same solution as above. We use an array for edgeCnt instead of a map.
    public List<Integer> findMinHeightTreesAlt(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        LinkedList<Integer> leaves = new LinkedList<Integer>();
        int[] edgeCnt = new int[n];

        for(int[] edge : edges) {
            if(!adjList.containsKey(edge[0])) {
                adjList.put(edge[0], new ArrayList<Integer>());
            }
            if(!adjList.containsKey(edge[1])) {
                adjList.put(edge[1], new ArrayList<Integer>());
            }
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
            edgeCnt[edge[0]]++;
            edgeCnt[edge[1]]++;
        }

        if(adjList.isEmpty()) { //Edge case where there is only one node.
            leaves.add(0);
            return leaves;
        }

        for(int i = 0; i < n; i++) {
            if(edgeCnt[i] == 1) { //Add to leaf nodes if there's only one neighbour.
                leaves.add(i);
            }
        }

        while(n > 2) {
            int length = leaves.size();
            for(int i = 0; i < length; i++) {
                int node = leaves.pop();
                n--;                    //We are eliminating this leaf node.
                for(int nei : adjList.get(node)) {
                    edgeCnt[nei]--; //Since we eliminated this node, we remove it from it's respective neighbours and thus reduce count.
                    if(edgeCnt[nei] == 1) { //Add to leaf list, if this neighbour becomes a leaf.
                        leaves.add(nei);
                    }
                }
            }
        }

        return leaves;
    }
}

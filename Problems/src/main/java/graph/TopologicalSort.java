package graph;

import java.util.*;

public class TopologicalSort {
    /*
    https://www.geeksforgeeks.org/problems/topological-sort/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=topological-sort
     In topological sort, we essentially backtrack. Find nodes that don't point to anything(say inde), add it,
        then nodes that point to inde recursively and backtrack. So when we get it in the stack,
        all independent nodes will be first and all parent nodes will be after that.
     */

    //This uses DFS.
    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        List<Integer>[] adjList = new List[V];
        Set<Integer> visited = new HashSet<Integer>();
        LinkedList<Integer> sortStack = new LinkedList<Integer>();

        ArrayList<Integer> returnList = new ArrayList<Integer>();

        for(int[] edge : edges) {
            if(adjList[edge[0]] == null) {
                adjList[edge[0]] = new ArrayList<Integer>();
            }
            adjList[edge[0]].add(edge[1]);
        }

        for(int i = 0; i < adjList.length; i++) {
            dfs(i, sortStack, adjList, visited);
        }


        while(!sortStack.isEmpty()) {
            returnList.add(sortStack.pop());
        }

        return returnList;
    }

    public static void dfs(int key, LinkedList<Integer> sortStack, List<Integer>[] adjList, Set<Integer> visited) {
        if(visited.contains(key)) {
            return;
        }
        visited.add(key);

        if(adjList[key] != null) {
            for(int i = 0; i < adjList[key].size(); i++) {
                dfs(adjList[key].get(i), sortStack, adjList, visited);
            }
        }

        sortStack.push(key);
    }

    //Also known as Kahn's algorithm.
    public static ArrayList<Integer> topoSortBFS(int V, int[][] edges) {
        List<Integer>[] adjList = new List[V];
        int[] inDegree = new int[V];
        LinkedList<Integer> sortQueue = new LinkedList<Integer>();

        ArrayList<Integer> returnList = new ArrayList<Integer>();

        for(int[] edge : edges) {
            if(adjList[edge[0]] == null) {
                adjList[edge[0]] = new ArrayList<Integer>();
            }
            adjList[edge[0]].add(edge[1]);
            inDegree[edge[1]]++;
        }

        for(int i = 0; i < V; i++) {
            if(inDegree[i] == 0) {
                sortQueue.add(i);
            }
        }

        while(!sortQueue.isEmpty()) {
            int curr = sortQueue.pop();
            returnList.add(curr);

            if(adjList[curr] != null) {
                for(int neighbour : adjList[curr]) {
                    inDegree[neighbour]--;
                    if(inDegree[neighbour] == 0) { //Add once all parents pointing to it are covered.
                        sortQueue.add(neighbour);
                    }
                }
            }

        }
        /*
        Adding to queue is conditional. Wouldn't queue become empty while there are elements left to be checked?
        Well no. Because they are Directed Acyclic graphs, if a node has multiple parent nodes,
            one of them is bound to have degree(number of parent nodes) of zero.
         */

        return returnList;
    }
}

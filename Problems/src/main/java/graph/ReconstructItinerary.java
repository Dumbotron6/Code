package graph;

import java.util.*;

public class ReconstructItinerary {
    /*
    https://leetcode.com/problems/reconstruct-itinerary/description/
     */

    //Revise.
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> adjList = new HashMap<String, PriorityQueue<String>>();

        for(int i = 0; i < tickets.size(); i++) {
            String source = tickets.get(i).get(0);
            String dest = tickets.get(i).get(1);
            if(!adjList.containsKey(source)) {
                adjList.put(source, new PriorityQueue<String>());
            }
            adjList.get(source).offer(dest);
        }

        List<String> path = new ArrayList<String>();
        dfs("JFK", adjList, path);

        return path;
    }

    /*
    We go as deep as possible for each destination. Since we are polling, we will not revisit the same airport unless another ticket leads us to it.
    This functions as a substitute for 'visited' set. Take example of JFK <-> ALK, JFK -> BOR. We go from JFK to ALK, but we return only when we have return
        ticket to JFK. Since we already polled ALK from JFK, we won't visit ALK again.
    Since we add to path only when dfs for that source is exhausted, won't run into dead ends prematurely. Like the above example, if we had gone to BOR
        first, we'd be stuck at BOR and wouldn't be able to return to JFK.
    */
    public void dfs(String source, HashMap<String, PriorityQueue<String>> adjList, List<String> path) {

        PriorityQueue<String> destinations = adjList.getOrDefault(source, null);

        while(destinations != null && !destinations.isEmpty()) {
            String nextSource = destinations.poll();
            dfs(nextSource, adjList, path);
        }

        path.add(0, source);
    }

    /*
    Take example [["JFK","SFO"],["JFK","ATL"],["ATL","JFK"]]. We go to JFK -> ATL -> JFK -> SFO.
    Now ATL dfs happens first, which goes back to JFK, which then goes into SFO.
    Take example [["JFK","SFO"],["JFK","ATL"],["SFO","JFK"]].
    ATL dfs happens first, which is a dead end. But JFK isn't added as that pq isn't exhausted. Then SFO dfs happens, which loops back to JFK,
        Now JFK is added as it's PQ is exhausted. Then SFO is added, then the original caller JFK is added.
    JFK(adj2size) -> ALK(added) -> JFK(adj1size) -> SF0(adj1size) -> JFK(adj0size so added) -> SF0(adj0size so added) -> original caller JKF added.
    Since this is recursion, usually head would be added to tail which would mean reversing the list.
    Instead we do add(0, source) which inserts at head.
    */

}
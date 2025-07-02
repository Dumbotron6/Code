package graph;

import java.util.*;

public class NetworkDelayTime {
    /*
    https://leetcode.com/problems/network-delay-time/
     */

    /*
    This solution uses Dijkstra's algorithm to find the shortest path to each element.
    */
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<WeightPair>> adjList = new HashMap<Integer, List<WeightPair>>(); //Stores a graph.
        int minTime = 0;

        for(int[] time : times) { //Add each node to graph with its neighbours.
            if(!adjList.containsKey(time[0])) {
                adjList.put(time[0], new ArrayList<WeightPair>());
            }
            adjList.get(time[0]).add(new WeightPair(time[1],time[2]));
        }

        //A minHeap lets us iterate through elements in order of shortest time to longest.
        PriorityQueue<WeightPair> timeWeight = new PriorityQueue<WeightPair>((a,b) -> a.time-b.time);
        //If we needed the shortest time to reach each node, we'd use map to store the time.
        Set<Integer> shortestTimeFound = new HashSet<Integer>();
        timeWeight.offer(new WeightPair(k, 0)); //The first node will have time 0.

        while(!timeWeight.isEmpty()) {
            WeightPair currWeight = timeWeight.poll();
            /*
            Since we use minHeap, even if there are multiple ways to reach a node, the shortest time would appear first in the minHeap.
            So if it's already added to the found set, the subsequent ones become irrelevant.
            */
            if(shortestTimeFound.contains(currWeight.node)) {
                continue;
            }

            shortestTimeFound.add(currWeight.node);
            minTime = Math.max(minTime, currWeight.time); //The max time out of all nodes is the min time to reach all nodes.

            //Add neighbours to heap, with time as current plus the parent node time that we came from.
            for(WeightPair neighbour : adjList.getOrDefault(currWeight.node, new ArrayList<WeightPair>())) {
                timeWeight.offer(new WeightPair(neighbour.node, neighbour.time+currWeight.time));
            }
        }

        if(shortestTimeFound.size() != n) { //If all nodes are reached from k, then we'd have found the shortest time for all.
            return -1;
        }
        return minTime;
    }

    class WeightPair {
        int node;
        int time;
        public WeightPair(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    //NOTE: Instead of adjList map, and getOrDefault, we can use an ArrayList since the constraints are small.
}

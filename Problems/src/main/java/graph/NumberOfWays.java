package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class NumberOfWays {
    /*
    https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
     */

    public int countPaths(int n, int[][] roads) {
        if(n == 1) {
            return 1;
        }

        long[][] shortestTillIndex = new long[n][2]; //Has n origin points, shortest cost to here, number of paths with shortest cost to here.
        List<DestCost>[] adjList = new List[n]; //Connecting paths from all origin points. i is Origin, DestCost has Destination, Cost to reach destination.

        for(int[] road : roads) { //bi-directional roads, so add to list two ways.
            if(adjList[road[0]] == null) {
                adjList[road[0]] = new ArrayList<DestCost>();
            }
            if(adjList[road[1]] == null) {
                adjList[road[1]] = new ArrayList<DestCost>();
            }
            adjList[road[0]].add(new DestCost(road[1],road[2]));
            adjList[road[1]].add(new DestCost(road[0],road[2]));
        }

        for(int i = 1; i < n; i++) {
            shortestTillIndex[i][0] = Long.MAX_VALUE;
        }
        shortestTillIndex[0][0] = 0; //Cost to reach index 0 is 0;
        shortestTillIndex[0][1] = 1; //Can reach index 0 only one way.

        int shortestWays = 0;
        int mod = (int) 1e9 + 7;

        PriorityQueue<DestCost> pq = new PriorityQueue<DestCost>((a, b) -> Long.compare(a.cost,b.cost));
        pq.add(new DestCost(0,0));

        while(!pq.isEmpty()) {
            DestCost source = pq.poll();
            int index = source.index;
            long cost = source.cost;

            for(DestCost nei : adjList[index]) {
                long newCost = cost+nei.cost;
                long currCost = shortestTillIndex[nei.index][0];
                if(newCost < currCost) { //If new cost is less that previous cost to reach neighbour by any other way
                    shortestTillIndex[nei.index][0] = newCost; //New shortest cost to neighbour's index
                    shortestTillIndex[nei.index][1] = shortestTillIndex[index][1]; //Arrived from source to here, so will have same number of ways as source
                    pq.offer(new DestCost(nei.index, newCost));
                }else if(newCost == currCost) { //Same cost as old, meaning we arrived to shortest cost from another path
                    shortestTillIndex[nei.index][1] = (int)(((long)shortestTillIndex[nei.index][1]+shortestTillIndex[index][1])%mod);
                    //Add existing path number of ways and new path number of ways.
                }
            }
        }

        return (int)shortestTillIndex[n-1][1];
    }

    class DestCost {
        int index;
        long cost;

        public DestCost(int _index, long _cost) {
            this.index = _index;
            this.cost = _cost;
        }
    }
}

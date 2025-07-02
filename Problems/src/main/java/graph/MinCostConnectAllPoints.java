package graph;

import java.util.*;

public class MinCostConnectAllPoints {
    /*
    https://leetcode.com/problems/min-cost-to-connect-all-points/description/
     */

    /*
    This uses Prim's algorithm(Minimum spanning tree). This is almost Dijkstra's algorithm, only with extra steps.
    Slightly altered and better solution below.
     */
    public int minCostConnectPoints(int[][] points) {
        int minCost = 0;
        int n = points.length;
        Map<Integer, List<int[]>> adjList = new HashMap<Integer, List<int[]>>();

        /*
        Remember, i and j are not x and y coordinates. i and j are indexes in points. We don't need to remember the coordinates,
            only the indexes. Coordinates are only needed to calculate the distance.
        */
        for(int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for(int j = i+1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1-x2) + Math.abs(y1-y2);
                //Unweighted graph so add vice versa.
                adjList.computeIfAbsent(i, k -> new ArrayList<int[]>()).add(new int[]{dist, j});
                adjList.computeIfAbsent(j, k -> new ArrayList<int[]>()).add(new int[]{dist, i});
            }
        }

        //Index 0 has distance, 1 has source and 2 has destination.
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a,b) -> a[0]-b[0]); //Sort by distance.
        Set<Integer> visited = new HashSet<Integer>(); //Since constrains are small, we can also use a boolean array.
        visited.add(0);

        /*
        In Dijkstra, we'd add the source. Here, we don't know the source. We can pick any.
        So we add every neighbour of the source and then traverse the least path.
        */
        for(int[] dest : adjList.getOrDefault(0, new ArrayList<int[]>())) { //We can pick any point to start. Picked 0 here.
            minHeap.offer(new int[]{dest[0], 0, dest[1]}); //Distance, source, destination.
        }
        /*
        We can do that, or we can do the below. This will execute the above, only, inside the loop.
        minHeap.add(new int[]{0,0,0});
        */

        while(visited.size() < n) { //Till we have visited all points.
            int[] curr = minHeap.poll();
            int source = curr[1];
            int newSource = curr[2];

            if(visited.contains(newSource)) {
                continue;
            }
            visited.add(newSource);
            minCost += curr[0]; //Since we need the minimum distance and reaching this point means we picked the least path.

            for(int[] neighbour : adjList.getOrDefault(newSource, new ArrayList<int[]>())) {
                int newDist = neighbour[0];
                int newDest = neighbour[1];
                if(!visited.contains(newDest)) { //Destination not already visited via any other path, which would've been the least.
                    minHeap.offer(new int[]{newDist, newSource, newDest});
                }
            }
        }

        return minCost;
    }

    //This uses Prim's algorithm(Minimum spanning tree). This is almost Dijkstra's algorithm, only with extra steps.
    public int minCostConnectPointsAlt(int[][] points) {
        int minCost = 0;
        int n = points.length;
        Map<Integer, List<int[]>> adjList = new HashMap<Integer, List<int[]>>();

        /*
        Remember, i and j are not x and y coordinates. i and j are indexes in points. We don't need to remember the coordinates,
            only the indexes. Coordinates are only needed to calculate the distance.
        */
        for(int i = 0; i < n; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for(int j = i+1; j < n; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                int dist = Math.abs(x1-x2) + Math.abs(y1-y2);
                //Unweighted graph so add vice versa.
                adjList.computeIfAbsent(i, k -> new ArrayList<int[]>()).add(new int[]{dist, j});
                adjList.computeIfAbsent(j, k -> new ArrayList<int[]>()).add(new int[]{dist, i});
            }
        }

        //Index 0 has distance, 1 has source and 2 has destination.
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a,b) -> a[0]-b[0]); //Sort by distance.
        Set<Integer> visited = new HashSet<Integer>(); //Since constrains are small, we can also use a boolean array.

        /*
        In Dijkstra, we'd add the source. Here, we don't know the source.
        We can pick any point to start. Picked 0 here.
        */
        minHeap.offer(new int[]{0,0}); //Distance, destination.

        while(visited.size() < n) { //Till we have visited all points.
            int[] curr = minHeap.poll();
            int newSource = curr[1];

            if(visited.contains(newSource)) {
                continue;
            }
            visited.add(newSource);
            minCost += curr[0]; //Since we need the minimum distance and reaching this point means we picked the least path.

            for(int[] neighbour : adjList.getOrDefault(newSource, new ArrayList<int[]>())) {
                int newDist = neighbour[0];
                int newDest = neighbour[1];
                if(!visited.contains(newDest)) { //Destination not already visited via any other path, which would've been the least.
                    minHeap.offer(new int[]{newDist, newDest});
                }
            }
        }

        return minCost;
    }
}

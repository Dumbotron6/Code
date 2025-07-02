package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CheapestFightWithKSteps {
    /*
    https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
     */

    /*
    This uses BFS. We can't use Dijkstra because there is a limit k, the number of steps we can take.
    Dijkstra is not concerned with the steps, only the cost.
    So we have to use BFS to check all connected flights at one step.
    */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        List<DestPrice>[] adjList = new List[n];
        LinkedList<DestPrice> queue = new LinkedList<DestPrice>();
        int[] visited = new int[n]; //Stores visited destinations and their cheapest cost.

        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<DestPrice>();
            visited[i] = Integer.MAX_VALUE;
        }

        for(int[] flight : flights) { //Form graph of connecting flights.
            adjList[flight[0]].add(new DestPrice(flight[1], flight[2])); //Source, Destination, Price.
        }

        queue.add(new DestPrice(src, 0)); //Index 1 is cost so far to reach index 0.
        int stops = -1;
        int cheapest = Integer.MAX_VALUE;

        while(!queue.isEmpty() && stops <= k) {
            int qSize = queue.size();

            for(int i = 0; i < qSize; i++) { //Check all flights at current stop.
                DestPrice curr = queue.pop();
                if(curr.location == dst) { //If destination reached, check if price is cheaper than current cheapest.
                    cheapest = Math.min(cheapest, curr.price);
                }

                for(DestPrice dest : adjList[curr.location]) { //Iterate through all connecting destinations.
                    int newCost = curr.price + dest.price;
                    if(visited[dest.location] != Integer.MAX_VALUE) { //If this destination was already reached.
                        if(visited[dest.location] > newCost) { //Add to queue only if new cost is cheaper to reach this destination.
                            visited[dest.location] = newCost; //Replace the cheapest cost to reach this destination.
                            queue.add(new DestPrice(dest.location, newCost));
                        }
                    }else {
                        visited[dest.location] = newCost;
                        queue.add(new DestPrice(dest.location, newCost));
                    }
                }
            }
            stops++;
        }

        return cheapest == Integer.MAX_VALUE ? -1 : cheapest;
    }

    class DestPrice {
        int location;
        int price;

        public DestPrice(int location, int price) {
            this.location = location;
            this.price = price;
        }
    }
}

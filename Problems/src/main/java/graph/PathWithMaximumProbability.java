package graph;

import java.util.*;

public class PathWithMaximumProbability {

    /*
    This uses Dijkstra's algorithm. Instead of finding the minimum 'path', we find the maximum 'path'.
    'path' is the probability. So instead of minHeap, we use maxHeap.
    This is the straightforward Dijkstra implementation.
    Tweaked and better solution is below.
    */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        Map<Integer, List<EdgeWeight>> adjList = new HashMap<Integer, List<EdgeWeight>>();

        for(int i = 0; i < edges.length; i++) {//We add vice versa in graph as it is undirected, i.e, bi-directional.
            if(!adjList.containsKey(edges[i][0])) {
                adjList.put(edges[i][0], new ArrayList<EdgeWeight>());
            }
            adjList.get(edges[i][0]).add(new EdgeWeight(edges[i][1], succProb[i]));
            if(!adjList.containsKey(edges[i][1])) {
                adjList.put(edges[i][1], new ArrayList<EdgeWeight>());
            }
            adjList.get(edges[i][1]).add(new EdgeWeight(edges[i][0], succProb[i]));
        }

        PriorityQueue<EdgeWeight> maxHeap = new PriorityQueue<EdgeWeight>((a,b) -> Double.compare(b.prob,a.prob));
        maxHeap.offer(new EdgeWeight(start_node, 1.0)); //Already at start_node, so probability is 1.

        /*
        Why have a visited? Because the graph is undirected, which will lead to cyclic graph.
        We don't need to check nodes already visited, as we'd have already checked all paths from there
            and calculated the probabilities.
        */
        Set<Integer> visited = new HashSet<Integer>();

        while(!maxHeap.isEmpty()) {
            EdgeWeight curr = maxHeap.poll();
            if(curr.verti == end_node) {
                return curr.prob;
            }
            visited.add(curr.verti);

            for(EdgeWeight neighbour : adjList.getOrDefault(curr.verti, new ArrayList<EdgeWeight>())) {
                if(visited.contains(neighbour.verti)) {
                    continue;
                }
                double newProb = curr.prob*neighbour.prob; //Calculate probability from parent to neighbour.
                maxHeap.offer(new EdgeWeight(neighbour.verti, newProb));
            }
        }

        return 0;
    }

    /*
    This uses Dijkstra's algorithm. Instead of finding the minimum 'path', we find the maximum 'path'.
    'path' is the probability. So instead of minHeap, we use maxHeap.
    */
    public double maxProbabilityBetter(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<EdgeWeight>[] adjList = new ArrayList[n]; //We know size and each node is unique. So we can use this instead of map.

        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<EdgeWeight>();
        }

        for(int i = 0; i < edges.length; i++) {//We add vice versa in graph as it is undirected, i.e, bi-directional.
            int verti1 = edges[i][0], verti2 = edges[i][1];
            adjList[verti1].add(new EdgeWeight(verti2, succProb[i]));
            adjList[verti2].add(new EdgeWeight(verti1, succProb[i]));
        }

        Double[] maxProb = new Double[n]; //Store maximum possible probability to reach each node. Also acts as a visited set.

        PriorityQueue<EdgeWeight> maxHeap = new PriorityQueue<EdgeWeight>((a,b) -> Double.compare(b.prob,a.prob));
        maxHeap.offer(new EdgeWeight(start_node, 1.0)); //Already at start_node, so probability is 1.

        while(!maxHeap.isEmpty()) {
            EdgeWeight curr = maxHeap.poll();
            int verti = curr.verti;
            double currProb = curr.prob;

            if(maxProb[verti] != null) { //Skip if already visited.
                continue;
            }

            if(verti == end_node) {
                return currProb;
            }

            maxProb[verti] = currProb;

            for(EdgeWeight neighbour : adjList[verti]) {
                if(maxProb[neighbour.verti] == null) { //Add only if probability previously not calculated.
                    double newProb = currProb*neighbour.prob; //Calculate probability from parent to neighbour.
                    maxHeap.offer(new EdgeWeight(neighbour.verti, newProb));
                }
                //This actually lets us store maxProb for every node.
            }
        }

        return 0;
    }

    public double maxProbabilityBetterAlt(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        List<EdgeWeight>[] adjList = new ArrayList[n]; //We know size and each node is unique. So we can use this instead of map.

        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<EdgeWeight>();
        }

        for(int i = 0; i < edges.length; i++) {//We add vice versa in graph as it is undirected, i.e, bi-directional.
            int verti1 = edges[i][0], verti2 = edges[i][1];
            adjList[verti1].add(new EdgeWeight(verti2, succProb[i]));
            adjList[verti2].add(new EdgeWeight(verti1, succProb[i]));
        }

        Double[] maxProb = new Double[n]; //Store maximum possible probability to reach each node.
        maxProb[start_node] = 1.0;

        PriorityQueue<EdgeWeight> maxHeap = new PriorityQueue<EdgeWeight>((a,b) -> Double.compare(b.prob,a.prob));
        maxHeap.offer(new EdgeWeight(start_node, 1.0)); //Already at start_node, so probability is 1.

        while(!maxHeap.isEmpty()) {
            EdgeWeight curr = maxHeap.poll();
            int verti = curr.verti;
            double currProb = curr.prob;

            if(verti == end_node) {
                return currProb;
            }

            if(maxProb[verti] != null) {
                continue;
            }

            for(EdgeWeight neighbour : adjList[verti]) {
                if(maxProb[neighbour.verti] != null) { //Add only if probability to reach neighbour is better than previous probability.
                    double newProb = currProb*neighbour.prob; //Calculate probability from parent to neighbour.
                    maxHeap.offer(new EdgeWeight(neighbour.verti, newProb));
                    maxProb[neighbour.verti] = newProb;
                }
                //This if conditions lets us avoid using a visited set. This actually lets us store maxProb for every node.
            }
        }

        return 0;
    }

    class EdgeWeight {
        int verti;
        double prob;

        public EdgeWeight(int verti, double prob) {
            this.verti = verti;
            this.prob = prob;
        }
    }
}

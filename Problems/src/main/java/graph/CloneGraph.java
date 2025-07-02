package graph;

import java.util.*;

public class CloneGraph {
    /*
    https://leetcode.com/problems/clone-graph/description/
     */

    //This uses BFS.
    public Node cloneGraph(Node node) {
        if(node == null) {
            return null;
        }

        Map<Integer, Node> newNodes = new HashMap<Integer, Node>(); //Keep track of newly created nodes.
        LinkedList<Node> queue = new LinkedList<Node>();
        //The queue is used to keep track of all newly created nodes that are yet to be assigned neighbours.

        Node returnNode = new Node(node.val, new ArrayList<Node>()); //Create the first node and add it to the map.
        newNodes.put(node.val, returnNode);

        queue.add(node);

        while(!queue.isEmpty()) {
            Node curr = queue.pop();
            Node currNew = newNodes.get(curr.val);
            for(Node neighbour : curr.neighbors) { //Loop through the neighbours.
                if(!newNodes.containsKey(neighbour.val)) { //If neighbour doesn't exist in newNode, ie hasn't been cloned yet, create a clone.
                    newNodes.put(neighbour.val, new Node(neighbour.val, new ArrayList<Node>()));
                    queue.add(neighbour); //Since the node is newly created, we need to add neighbours to it. So add it to queue.
                    //We only add newly cloned to queue as we don't want to revisit the same node again.
                }
                currNew.neighbors.add(newNodes.get(neighbour.val)); //Take the already/newly cloned node, assign it as a neighbour.
            }
        }

        return returnNode;
    }

    //This uses DFS.
    public Node cloneGraphDFS(Node node) {
        if(node == null) {
            return null;
        }
        Map<Integer, Node> newNodes = new HashMap<Integer, Node>(); //Keep track of newly created nodes.
        return dfs(node, newNodes);
    }

    public Node dfs(Node node, Map<Integer, Node> newNodes) {
        if(newNodes.containsKey(node.val)) { //If node already cloned, we don't need to clone again/add neighbors to it.
            return newNodes.get(node.val);
        }

        Node copy = new Node(node.val, new ArrayList<Node>());
        newNodes.put(copy.val, copy);

        for(Node neighbor : node.neighbors) { //Since node is newly cloned, add neighbors to it.
            copy.neighbors.add(dfs(neighbor, newNodes));
        }

        return copy;
    }
}

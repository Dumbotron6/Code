package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    /*
    https://leetcode.com/problems/lru-cache/description/
     */

    int capacity;
    Map<Integer, Node> cache; //Has Key and respective node.
    Node head, tail; //head.prev will be most recently used and tail.next will be least recently used.

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<Integer, Node>();
        head = new Node(0, 0, null, null);
        tail = new Node(0, 0, null, null);
        head.prev = tail;
        tail.next = head;
    }

    public int get(int key) {
        if(!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveNodeToHead(node); //Move to node being checked to head.
        return node.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value, head.prev, head);
        if(cache.containsKey(key)) {
            node = cache.get(key);
            node.value = value;
        }else {
            cache.put(key, node);
        }
        moveNodeToHead(node); //Move this node to head.
        if(cache.size() > capacity) { //This takes the last node and removes it from the cache and from the LL.
            Node removingNode = tail.next;
            cache.remove(removingNode.key);
            removingNode.next.prev = tail;
            tail.next = removingNode.next;
        }
    }

    public void moveNodeToHead(Node node) { //This moves the node to the head ie, most recently used.
        node.prev.next = node.next;
        node.next.prev = node.prev; //These two remove it from current position.
        node.prev = head.prev;
        node.next = head;
        head.prev.next = node;
        head.prev = node; //These four place it in head position.
    }
    //NOTE: These two can be split into two separate functions. Refer LFUCache for that modification.

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value, Node prev, Node next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}

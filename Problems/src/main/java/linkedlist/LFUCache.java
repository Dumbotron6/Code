package linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {
    /*
    https://leetcode.com/problems/lfu-cache/description/
     */

    /*
    Study LRUCache before this.
    NOTE: Once size becomes 0, there's no need to remove from freqMap as it being there won't affect our logic.
    It will take up unnecessary space but reduces complexity for this problem.
    Keeping that in mind, consider refactoring to understand it better.
    Also think about edge case where capacity == 0, though in problem constraints it's given that it will be minimum 1.
     */
    int capacity;
    int currCapacity;
    int minFreq;
    Map<Integer, LRUCache> freqMap; //To store all nodes of each frequency.
    Map<Integer, Node> keyNode; //To store the node, and it's respective frequency.

    public LFUCache(int capacity) {
        this.capacity = capacity;
        currCapacity = 0;
        minFreq = 0;
        freqMap = new HashMap<Integer, LRUCache>();
        keyNode = new HashMap<Integer, Node>();
    }

    public int get(int key) {
        if(keyNode.containsKey(key)) {
            Node node = keyNode.get(key);
            put(node.key, node.value); //Reusing put function to simply increment the frequency of node.
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        int currFreq = 1;
        int newFreq = 1;
        Node node = new Node(key, value, 1);

        if(!keyNode.containsKey(key)) { //Create key and increase capacity.
            keyNode.put(key, node);
            currCapacity++;
            if(currCapacity > capacity) { //If capacity exceeds, remove current LRU of minFreq cache.
                currCapacity--;
                int removeKey = freqMap.get(minFreq).removeLast();
                keyNode.remove(removeKey);
            }
            minFreq = 1;
        }else {
            node = keyNode.get(key);
            node.value = value;
            currFreq = node.freq;
            newFreq = currFreq+1; //Increase node's frequency.
            node.freq = newFreq;

            freqMap.get(currFreq).remove(node); //Remove from current frequency cache.
        }

        if(!freqMap.containsKey(newFreq)) { //Create new frequency cache if it doesn't exist.
            freqMap.put(newFreq, new LRUCache());
        }

        freqMap.get(newFreq).addFront(node); //Add node to newFrequency cache front.

        if(freqMap.get(currFreq).size == 0) { //Size of frequency cache is zero.
            freqMap.remove(currFreq); //Remove that freqency from map.
            if(minFreq == currFreq) { //If that was the min frequency, increment it.
                minFreq++;
            }
        } //Will beccome 0 only at get or put which both decrement freq by 1. So save to say minFreq = currFreq+1;
    }

    class LRUCache {
        Node head;
        Node tail;
        int size;

        public LRUCache() {
            head = new Node(0,0,0);
            tail = new Node(0,0,0);
            head.prev = tail;
            tail.next = head;
            size = 0;
        }

        public void addFront(Node node) { //Add to front ie, recently used.
            Node left = head.prev;
            node.next = head;
            node.prev = left;
            left.next = node;
            head.prev = node;
            size++;
        }

        public int removeLast() { //Remove last node ie, LRU node.
            Node node = tail.next;
            remove(node);
            return node.key;
        }

        public void remove(Node node) { //Remove node from position.
            Node left = node.prev;
            Node right = node.next;
            left.next = right;
            right.prev = left;
            size--;
        }
    }

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        int freq;

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
}

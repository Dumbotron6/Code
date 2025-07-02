package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class MedianFromDataStrem {
    /*
    https://leetcode.com/problems/find-median-from-data-stream/
    NOTE: This can be further optimized slightly by having largeFirst hold +1 extra value instead of
            always checking which heap has the extra value - addNumAlt and findMedianAlt.
     */

    class MedianFinder {

        Queue<Integer> smallFirst; //Stores number from low to high like, to top element will be low, like 6,7,8. Top 6.
        Queue<Integer> largeFirst; //Stores number from high to low like, to top element will be high, like 3,2,1. Top 3.
        /*
        So largeFirst and smallFirst will have 1,2,3 and 6,7,8. Any new element added also will be split down the middle.
        Example, 3,2,1 and 5,6,7,8.
        If 2 is added, it will be added to smallFirst first. SO 3,2,1 and 2,5,6,7,8. Then the while check will happen.
        It will then become 3,2,2,1 and 5,6,7,8.
        */

        public MedianFinder() {
            smallFirst = new PriorityQueue<Integer>((a, b) -> a-b);
            largeFirst = new PriorityQueue<Integer>((a,b) -> b-a);
        }

        /*
        We add the element to smallFirst first. Then we check it should go to largeFirst by seeing if peek is greater than smallFirst peek.
        Since it is split down the middle, smallFirst peek should always be greater than largeFirst peek.
        Checking if size diff is greater than 1 is to maintain equal sizes if even numbers, +1 in either heap if odd number of elements.
        */
        public void addNum(int num) {
            smallFirst.add(num);
            if(smallFirst.size() - largeFirst.size() > 1 ||
                    (!largeFirst.isEmpty() && smallFirst.peek() < largeFirst.peek())) {
                largeFirst.add(smallFirst.poll());
            }
            if(largeFirst.size() - smallFirst.size() > 1) {
                smallFirst.add(largeFirst.poll());
            }
        }

        public double findMedian() {
            if(smallFirst.size() == largeFirst.size()) {
                return (double)(smallFirst.peek()+largeFirst.peek())/2;
            }
            if(smallFirst.size() > largeFirst.size()){
                return (double)smallFirst.peek();
            }else {
                return (double)largeFirst.peek();
            }
        }

        /*
        Building on the previously submitted solution, we make a slight alteration
              by having largeFirst hold +1 extra value instead of
              always checking which heap has the extra value - addNumAlt and findMedianAlt.
         */
        public void addNumAlt(int num) {
            if(smallFirst.isEmpty() || num >= smallFirst.peek()) {
                smallFirst.add(num);
            }else {
                largeFirst.add(num);
            }

            //If smallFirst exceeds+1 size.
            if(smallFirst.size() - largeFirst.size() > 1) {
                largeFirst.add(smallFirst.poll());
            }
            //Always ensure largeFirst holds the +1 value.
            if(largeFirst.size() > smallFirst.size()) {
                smallFirst.add(largeFirst.poll());
            }
        }

        public double findMedianAlt() {
            if(smallFirst.size() == largeFirst.size()) {
                return (double)(smallFirst.peek()+largeFirst.peek())/2;
            }
            return (double)smallFirst.peek();
        }
    }

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}

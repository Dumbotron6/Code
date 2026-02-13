package heap;

import java.util.PriorityQueue;

public class CostToHireWorkers {
    /*
    https://leetcode.com/problems/total-cost-to-hire-k-workers/description/
     */

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> left = new PriorityQueue<Integer>();
        PriorityQueue<Integer> right = new PriorityQueue<Integer>();

        int leftMax = 0;
        int rightMax = costs.length-1;

        //Fill first 'candidates' costs.
        while(leftMax < candidates) {
            left.offer(costs[leftMax++]);
        }

        //Fill last 'candidates' costs. Only fill those not already covered by left. This happens when costs.length < 2*candidates (not enough candidates).
        while(rightMax > costs.length-1-candidates && rightMax >= leftMax) {
            right.offer(costs[rightMax--]);
        }

        int hired = 0;
        long minCost = 0;

        while(!left.isEmpty() && !right.isEmpty() && hired < k) {
            //Pick left if left is greater or left and right are equal(thereby picking the lower index.).
            if(left.peek() <= right.peek()) {
                minCost += left.poll();
                if(leftMax <= rightMax) { //Fill only if elements not already covered by right.
                    left.offer(costs[leftMax++]);
                }
            }else { //Pick right if right is greater.
                minCost += right.poll();
                if(leftMax <= rightMax) { //Fill only if elements not already covered by left.
                    right.offer(costs[rightMax--]);
                }
            }
            hired++;
        }

        while(hired < k) {
            if(!left.isEmpty()) { //Fill from left first since their indexes are lower.
                minCost += left.poll();
            }else if(!right.isEmpty()) {
                minCost += right.poll();
            }else {
                break;
            }
            hired++;
        }

        return minCost;
    }

    /*
    NOTE: Why leftMax <= rightMax? Because at any time, leftMax and rightMax are +1(or -1 for right) from the last element added to left and right.
            So leftMax and rightMax are not yet added into left and right respectively. So safe to compare leftMax <= rightMax.
            If that is false, it means all elements in array have been added to either left or right.
    */
}

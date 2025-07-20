package greedy;

import java.util.PriorityQueue;

public class FurthestBuilding {
    /*
    https://leetcode.com/problems/furthest-building-you-can-reach/description/
     */

    //Revise.
    /*
    We need to use the ladders for the largest gaps, while using bricks for the smallest.
    We use up ladders for all, when we run out, we check if any of the previous gaps could be filled by bricks
        instead. If so, we fill that with bricks, take that ladder, use it now.
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> usedLadderHeight = new PriorityQueue<Integer>();

        for(int i = 1; i < heights.length; i++) {
            if(heights[i] > heights[i-1]) {
                int brickNeed = heights[i]-heights[i-1];
                if(ladders > 0) { //Greedily use ladders.
                    ladders--;
                    usedLadderHeight.offer(brickNeed);
                }else { //If ladders used up.
                    //Take previous ladder used for smallest height, replace with bricks, use that ladder now.
                    if(!usedLadderHeight.isEmpty() && usedLadderHeight.peek() < brickNeed) {
                        int newBrickNeed = brickNeed;
                        brickNeed = usedLadderHeight.poll();
                        usedLadderHeight.offer(newBrickNeed);
                    }
                    if(brickNeed <= bricks) {
                        bricks -= brickNeed;
                    }else {
                        return i-1;
                    }
                }
            }
        }

        return heights.length-1;
    }
}

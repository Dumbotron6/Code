package stack;

import java.util.LinkedList;

public class Pattern132 {
    /*
    The condition is, i < j > k. So till we find a j, keep adding to stack.
    When we do find j, keep popping till we find maximum k.
    ie, whenever we find a j > k, the top of the stack will have the max element and kEle will have the second max element.
    Now all we have to do is find an i that is less than both.
    */
    public boolean find132pattern(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int kEle = Integer.MIN_VALUE;

        for(int i = nums.length-1; i >= 0; i--) {
            if(!stack.isEmpty() && nums[i] < stack.peek() && nums[i] < kEle) {
                return true;
            }
            while(!stack.isEmpty() && nums[i] > stack.peek()) { //We found a max. So keep popping to find secondMax.
                kEle = Math.max(kEle, stack.pop()); //For example, in 4,1,2,3 when 4 is nums[i], kEle will be 3.
            }
            /*
            Now all we have to do is keep looking till we find something less than 4 and 3.
            Or when something > 4 is found, say 6, stack.peek() will become 6 and kEle will become 4.
            */
            stack.push(nums[i]);
        }

        return false;
    }

    //Below is the same as above. Only removed some unnecessary checks.
    public boolean find132patternImproved(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int kEle = Integer.MIN_VALUE;

        for(int i = nums.length-1; i >= 0; i--) {
            if(nums[i] < kEle) { //If kEle > Integer.MIN_VALUE, it means we have a max and second max, ie, j and k.
                return true;
            }
            while(!stack.isEmpty() && nums[i] > stack.peek()) { //We found a max. So keep popping to find secondMax.
                kEle = stack.pop(); //For example, in 4,1,2,3 when 4 is nums[i], kEle will be 3.
            }
            /*
            Now all we have to do is keep looking till we find something less than 4 and 3.
            Or when something > 4 is found, say 6, stack.peek() will become 6 and kEle will become 4.
            */
            stack.push(nums[i]);
        }

        return false;
    }
}

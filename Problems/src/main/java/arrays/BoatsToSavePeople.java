package arrays;

import java.util.Arrays;

public class BoatsToSavePeople {
    /*
    https://leetcode.com/problems/boats-to-save-people/description/
     */

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);

        int left = 0;
        int right = people.length-1;
        int boat = 0;

        /*
        The least weight and most weight should be grouped together to check limit.
        If weight is exceeded the most weight needs to be in a separate boat.
        If not, lease weight can be added in the same boat.
        When left == right, if weight is 4 and limit is 6, people[right] + people[left] becomes 8.
        Had this doubt. But no worries as boat is still incremented.
        */
        while(right >= left) {
            if(people[right] + people[left] <= limit) {
                left++;
            }
            boat++;
            right--;
        }

        return boat;
    }
}

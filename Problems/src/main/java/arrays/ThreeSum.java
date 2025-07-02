package arrays;

import java.util.*;

public class ThreeSum {
    /*
    https://leetcode.com/problems/3sum/
     */

    /*
    When we store a list of integers in a set, the value of the integers are compared.
    Since the values are compared, the set does not store duplicate lists.
    The order of the integers in the list matter to the set. So we sort the list before inserting into set.
     */
    public List<List<Integer>> threeSumBetter(int[] nums) {

        Set<List<Integer>> finalSet = new HashSet<List<Integer>>();
        Map<Integer, Integer> diffMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++) {
            diffMap.put(nums[i], i);
        }

        for(int i = 0; i < nums.length; i++) {
            int finalVal = nums[i];
            for(int j = i+1; j < nums.length; j++) {
                if(diffMap.containsKey(-1*(nums[i]+nums[j]))) {
                    int location = diffMap.get(-1*(nums[i]+nums[j]));
                    if(location > i && location > j) {
                        List<Integer> tempList = new ArrayList<Integer>();
                        tempList.add(nums[i]);
                        tempList.add(nums[j]);
                        tempList.add(nums[location]);
                        Collections.sort(tempList);
                        finalSet.add(tempList);
                    }
                }
            }
        }

        return new ArrayList(finalSet);

    }

    /*
    In this solution, we sort the array so duplicates are easier to trace and we can move left or right
        instead of iterating and finding the sum over the whole array.
     */
    public List<List<Integer>> threeSumOptimal(int[] nums) {

        Arrays.sort(nums);
        List<List<Integer>> finalList = new ArrayList<List<Integer>>();

        for(int i = 0; i < nums.length; i++) {
            if(i == 0 || nums[i] != nums[i-1]) {
                int findVal = nums[i];
                int left = i+1;
                int right = nums.length-1;

                while(left < right) {
                    int sum = findVal + nums[left] + nums[right];
                    //Our target is 0. So when adding 3 elements, if we get 0, we add to final list.
                    if(findVal + nums[left] + nums[right] == 0) {
                        finalList.add(Arrays.asList(findVal, nums[left], nums[right]));
                        left++; right--;

                        //These two while loops skip duplicates ie. Elements that were just added to final list.
                        while(left < right && nums[left] == nums[left-1])
                            left++;
                        while(left < right && nums[right] == nums[right+1])
                            right--;
                    /*
                    Since it is a sorted array, if the sum is less, we can just increment from the left.
                        ie. Pick a higher number from the left to increase the sum.
                    Decrement if sum is greater.
                        ie. Pick a smaller number from the right to decrease the sum.
                     */
                    }else if(sum < 0) {
                        left++;
                    }else if(sum > 0) {
                        right--;
                    }
                }
            }
        }
        return finalList;
    }
}

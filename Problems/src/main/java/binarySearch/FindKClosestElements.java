package binarySearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    /*
    https://leetcode.com/problems/find-k-closest-elements/description/
    Revise this problem.
     */

    //We use two pointers. Time complexity is O(N). Optimal solution using binary search is below.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length-1;
        List<Integer> result = new ArrayList<Integer>();

        /*
        The intuition is, the greater the number deviates from x on either side, the greater the abs value will be.
        We start shrinking from the extreme ends till we have k elements.
        We don't need to worry about the actual x value. What we care about is the abs difference value.
        To shrink left or right depends on the problem statement. If left has lesser value, we need to keep it and shrink from the right.
        If right has lesser value, we keep it and shrink from the left.
        If it's equal, we keep the lease element which will be on the left.
        */
        while(right - left + 1 > k) {
            if(Math.abs(arr[left]-x) <= Math.abs(arr[right]-x)) {
                right--;
            }else {
                left++;
            }
        }

        for(int i = left; i <= right; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    //Binary search solution. Time complexity is O(log(N-k)+k).
    public List<Integer> findClosestElementsBinarySearch(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length-k; //Else mid+k will go out of bounds.
        List<Integer> result = new ArrayList<Integer>();

        while(left < right) {
            int mid = left+(right-left)/2;
            /*
             If arr[mid] is closer to x than arr[mid + k],
                that means arr[mid + k], as well as every element to the right of it can never be in the answer.
             This means we should move our right pointer to avoid considering them.
             Since we know arr[mid] is lesser, we need to check to the left of mid to check if there is even lesser.
             If add[mid] is greater, then it means arr[mid-k] is closer to x. So we move towards [mid-k] by moving left to mid.
             Essentially, we consider mid as the first element.
             We keep moving towards the least difference possible(x-arr[i]).
            */
            if(x-arr[mid] > arr[mid+k]-x) {
                left = mid+1;
            }else {
                right = mid;
            }
        }

        for(int i = right; i < right+k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    /*
    Take example [1,2,3,4,6] and x = 6 and k = 2.
    At first loop, left will be 0 and right will be 3, mid will be 1. Now 6-arr[mid] is 4 while arr[mid+k]-6 is -2.
    So we move towards right. Nothing to the left arr[mid] will ever be closer to x.
    If x was 2, then 2-add[mid] will be 0 while arr[mid+k]-2 will be 2.
    Nothing to the right of arr[mid+k] will ever be closer to x.
    */
}

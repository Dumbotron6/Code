package arrays;

public class CountInversion {
    /*
    https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=inversion-of-array
     */

    /*
    While sorting, we will be comparing elements to see if they are greater or smaller which is ultimately what we need.
    If we use merge sort, left and right array are compared for greater elements.
    Since left array is already sorted, say [2,4,5,6,7] and in right array we have [3,8], 3 occurs after 4,5,6,7.
    Meaning, there are 4 possible count inversions [4,3],[5,3],[6,3],[7,3].
    During merge, 3 will be inserted before 4. Count of inversion elements is 4 and everything after it,
        which is 4,5,6,7.
     */
    static long inversionCount(long arr[], int n) {
        long[] count = new long[] {0};
        mergeSort(arr, 0, n-1, count);

        return count[0];
    }

    static void mergeSort(long arr[], int low, int high, long[] count) {
        int mid = low + (high-low)/2;

        if(high > low) {

            mergeSort(arr, low, mid, count);

            mergeSort(arr, mid+1, high, count);

            merge(arr, low, mid+1, high, count);
        }
    }

    static void merge(long arr[], int low, int mid, int high, long[] count) {
        long[] temp1 = new long[mid - low];
        long[] temp2 = new long[high - mid + 1];

        for(int i = 0; i < temp1.length; i++)
            temp1[i] = arr[low+i];

        for(int i = 0; i < temp2.length; i++)
            temp2[i] = arr[mid+i];

        int i = 0;
        int j = 0;
        while(i < temp1.length && j < temp2.length) {
            if(temp1[i] <= temp2[j]) {
                arr[low++] = temp1[i++];
            }else {
                arr[low++] = temp2[j++];
                count[0] += temp1.length - i;
            }
        }

        while(i < temp1.length) {
            arr[low++] = temp1[i++];
        }

        while(j < temp2.length) {
            arr[low++] = temp2[j++];
        }
    }

}

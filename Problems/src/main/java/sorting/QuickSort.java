package sorting;

import java.util.Arrays;

public class QuickSort {
//        public static void sort() {
//        int[] array = new int[] {5,6,4,9,1,3,2,8,10,7};
//
//        System.out.printf("Initial array : %s%n", Arrays.toString(array));
//
//        //Passing from 0 to the last element so length-1
//        quickSort(array, 0, array.length - 1);
//
//        System.out.printf("Sorted array : %s%n", Arrays.toString(array));
//    }
//
//
//   public static void quickSort(int[] array, int low, int high) {
//        if(high > low) {
//            int partition = getPartition(array, low, high);
//            quickSort(array, low, partition - 1);
//            quickSort(array, partition + 1, high);
//        }
//   }
//
//   public static int getPartition(int[] array, int low, int high){
//        int pivot = low + (high - low)/2;
//
//        int i = low;
//        int j = high;
//
//        swap(array, j, pivot);
//        pivot = j;
//        j--;
//
//        while(i <= j) {
//            if (array[pivot] < array[i] && array[pivot] > array[j]) {
//                swap(array, i, j);
//                i++;
//                j--;
//                continue;
//            }
//            if (array[pivot] >= array[i]) {
//                i++;
//            }
//            if (array[pivot] <= array[j]) {
//                j--;
//            }
//        }
//
//        swap(array, i, pivot);
//
//        return i;
//   }
//
//    public static void swap(int[] array, int i, int j) {
//        int temp = array[i];
//        array[i] = array[j];
//        array[j] = temp;
//   }

    public static void sort() {
        int[] arr = {5, 6, 4, 9, 1, 3, 2, 8, 10, 7};
        System.out.println("Original Array: " + Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);

        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2]; // Choose the middle element as pivot
        int i = low;
        int j = high;

        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }

            while (arr[j] > pivot) {
                j--;
            }

            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        // Move the pivot to its correct position
        while (j >= low && arr[j] >= pivot) {
            j--;
        }
        for (int k = j + 1; k <= high; k++) {
            swap(arr, k, i);
            i++;
        }

        return j + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

package sorting;

import java.util.Arrays;

public class MergeSort {

    public static void sort() {
        int[] array = new int[] {5,6,4,9,1,3,2,8,10,7};

        System.out.printf("Initial array : %s%n", Arrays.toString(array));

        //Passing from 0 to the last element so length-1
        mergeSort(array, 0, array.length - 1);

        System.out.printf("Sorted array : %s%n", Arrays.toString(array));
    }


    public static void mergeSort(int[] array, int low, int high) {

        int mid = low + (high-low)/2;
        if(high > low) {
            //Do from first to middle element(included)
            mergeSort(array, low, mid);
            //Do from middle element(excluded so +1) to last element
            mergeSort(array, mid + 1, high);
            //Pass indexes of two arrays to be merged from above
            merge(array, low, mid + 1, high);
        }
    }

    public static void merge(int[] array, int low, int mid, int high) {
        System.out.println("Low1: " + low + " High1: " + high + " Mid1: " + mid);

        int[] first = new int[mid - low]; //Temporary first array
        int[] second = new int[high - mid + 1]; //Temporary second array
        /*
            Reason for +1 above in size
            Ex. Array as [0,1,2,3,4] then low would be index 0, mid would be index 3 and high would be index 4
            First would be [0,1,2] -- mid-low = 3 size
            Second would be [3,4] -- high-mid+1 = 2 size
         */

        for(int i = 0 ; i < first.length; i++) {
            first[i] = array[low+i];
        }
        for(int i = 0 ; i < second.length; i++) {
            second[i] = array[mid+i];
        }

        //Printing both arrays for reference
        System.out.printf("First : %s%n", Arrays.toString(first));
        System.out.printf("Second : %s%n", Arrays.toString(second));

        int i = 0;
        int j = 0;
        int k = low;

        while(i < first.length && j < second.length) {
            if(first[i] < second[j]) {
                array[k] = first[i];
                i++;
            } else {
                array[k] = second[j];
                j++;
            }
            k++;
        }

        while(i < first.length) {
            array[k] = first[i];
            i++;
            k++;
        }
        while(j < second.length){
            array[k] = second[j];
            j++;
            k++;
        }

        //Printing swapped array for reference
        System.out.printf("Printing swapped array: %s%n", Arrays.toString(array));
    }

}

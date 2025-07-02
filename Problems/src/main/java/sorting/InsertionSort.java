package sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void sort() {
        int[] array = new int[] {5,6,4,9,1,3,2,8,10,7};

        System.out.printf("Initial array : %s%n", Arrays.toString(array));

        insertionSort(array);

        System.out.printf("Sorted array : %s%n", Arrays.toString(array));
    }

    //Place each element in its correct position by moving greater elements to right
    public static void insertionSort(int[] array) {
        for(int i = 1; i < array.length; i++) {
            //Hold the number that is being checked
            int temp = array[i];
            //Start checking from previous position
            int j = i-1;
            //Move numbers to right as long as it is greater
            while(j >= 0 && array[j] > temp) {
                array[j+1] = array[j];
                j--;
            }
            //j is now the place where the number is smaller than temp
            //So place temp at the next position
            array[j+1] = temp;
        }
    }


}

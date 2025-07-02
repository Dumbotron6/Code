package sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void sort() {
        int[] array = new int[] {5,6,4,9,1,3,2,8,10,7};

        System.out.printf("Initial array : %s%n", Arrays.toString(array));

        selectionSort(array);

        System.out.printf("Sorted array : %s%n", Arrays.toString(array));
    }

    //Move smallest element to the beginning
    public static void selectionSort(int[] array) {
        for(int i = 0; i < array.length-1; i++) {
            for(int j = i+1; j < array.length; j++) {
                if(array[j] < array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
    }

}

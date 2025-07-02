package sorting;

import java.util.Arrays;

public class BubbleSort {
    public static void sort() {
        int[] array = new int[] {5,6,4,9,1,3,2,8,10,7};

        System.out.printf("Initial array : %s%n", Arrays.toString(array));

        bubbleSort(array);

        System.out.printf("Sorted array : %s%n", Arrays.toString(array));
    }

    //Move largest element to the end
    public static void bubbleSort(int[] array) {
        for(int i = array.length-1; i > 0; i--) {
            for(int j = 0; j < i; j++) {
                if(array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

}

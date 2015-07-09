package madtest.common.sort;

import java.util.Arrays;

/**
 * Created by qct on 2015/3/15.
 */
public class SortTest {
    public static void main(String[] args) {

        int[] array = new int[]{6, 54, 3, 8, 35, 23, 0};

//        quickSort(array, 0, 6);
        bubbleSort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    //快速排序
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }

    }

    //快排  划分
    private static int partition(int[] array, int low, int high) {
        int pivot = array[low];
        while (low < high) {
            while (low < high && array[high] > pivot) {
                --high;
            }
            array[low] = array[high];
            while (low < high && array[low] < pivot) {
                ++low;
            }
            array[high] = array[low];
        }
        array[low] = pivot;
        return low;
    }

    //冒泡排序
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int k = array[i];
                    array[i] = array[j];
                    array[j] = k;
                }
            }
        }
    }
}

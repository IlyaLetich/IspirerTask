package org.ispirer.utils;

public class QuickSorter {
    public static void quickSortArrayOfBytes(byte[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSortArrayOfBytes(arr, low, pi - 1);
            quickSortArrayOfBytes(arr, pi + 1, high);
        }
    }

    public static int partition(byte[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                byte temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        byte temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

}

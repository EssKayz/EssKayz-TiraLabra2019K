/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.Structures;

/**
 *
 * @author ColdFish
 */
public class ArrayLib {

    private String[] sequences;
    private int length;

    /**
     * Sort the array with a QuickSort algorithm
     *
     * @param array the array to be sorted
     */
    public void QuickSort(String[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        this.sequences = array;
        this.length = array.length;
        qs(0, length - 1);
    }

    /**
     * Sort the array with a MergeSort algorithm
     * @param arr the array to be sorted    
     * @param start first index of the array
     * @param end last index of the array
     */
    public void sort(String[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = (start + end) / 2;

        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    /**
     * Merge the array
     * @param array the array to be merged
     * @param start first index
     * @param mid middle index
     * @param end last index
     */
    public static void merge(String[] array, int start, int mid, int end) {
        int n = end - start + 1;
        String[] help = new String[n];
        int firstIndex = start;
        int midIndex = mid + 1;
        int j = 0;

        while (firstIndex <= mid && midIndex <= end) {
            if (array[firstIndex].compareTo(array[midIndex]) < 0) {
                help[j] = array[firstIndex];
                firstIndex++;
            } else {
                help[j] = array[midIndex];
                midIndex++;
            }
            j++;
        }

        while (firstIndex <= mid) {
            help[j] = array[firstIndex];
            firstIndex++;
            j++;
        }

        while (midIndex <= end) {
            help[j] = array[midIndex];
            midIndex++;
            j++;
        }

        for (j = 0; j < n; j++) {
            array[start + j] = help[j];
        }
    }

    /**
     * Swap the places of two elements in the array
     *
     * @param i the index of first element to swap
     * @param j the index of the second element that swaps places with first one
     */
    private void swap(int i, int j) {
        String apu = this.sequences[i];
        this.sequences[i] = this.sequences[j];
        this.sequences[j] = apu;
    }

    /**
     * A quickSort for the sequence array
     *
     * @param lowIndx the first element of sort
     * @param highIndx the last element of sort
     */
    private void qs(int lowIndx, int highIndx) {
        int x = lowIndx;
        int y = highIndx;
        String mid = this.sequences[lowIndx + (highIndx - lowIndx) / 2];

        while (x <= y) {
            while (this.sequences[x].compareToIgnoreCase(mid) < 0) {
                x++;
            }

            while (this.sequences[y].compareToIgnoreCase(mid) > 0) {
                y--;
            }

            if (x <= y) {
                swap(x, y);
                x++;
                y--;
            }
        }

        //recurse quicksort method
        if (lowIndx < y) {
            qs(lowIndx, y);
        }
        if (x < highIndx) {
            qs(x, highIndx);
        }
    }
}

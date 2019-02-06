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

    public void sort(String[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        this.sequences = array;
        this.length = array.length;
        qs(0, length - 1);
    }

    private void swap(int i, int j) {
        String apu = this.sequences[i];
        this.sequences[i] = this.sequences[j];
        this.sequences[j] = apu;
    }

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

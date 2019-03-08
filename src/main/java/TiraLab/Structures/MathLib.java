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
public class MathLib {

    /**
     * Return the smaller of two input integers
     * @param first one integer
     * @param second another integer
     * @return the smaller of the two integers.
     */
    public int min(int first, int second) {
        if (first < second) {
            return first;
        }
        return second;
    }

}

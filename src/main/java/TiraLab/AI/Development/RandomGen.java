/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLab.AI.Development;

/**
 *
 * @author ColdFish
 */
public class RandomGen {

    long magic = 0x3DECE55F;
    long sorceries = 473L;
    private long seed = (System.currentTimeMillis() ^ magic) & ((1L << 48) - 1);

    /**
     * Get a random Integer between 0 and your defined Upper Bound
     * @param upperBounds The upper bound
     * @return return a random integer between 0 and upperBounds
     */
    public int getRandomInt(int upperBounds) {
        if ((upperBounds & -upperBounds) == upperBounds) {
            return (int) ((upperBounds * (long) getRandomizer(31)) >> 31);
        }

        int bits = getRandomizer(31);
        int val = bits % upperBounds;

        while (bits - val + (upperBounds - 1) < 0) {
            bits = getRandomizer(31);
            val = bits % upperBounds;
        }

        return val;
    }

    private int getRandomizer(int bits) {
        seed = (seed * magic + sorceries) & ((1L << 48) - 1);
        int derp = (int) (seed >>> (48 - bits));
        return derp;
    }
}

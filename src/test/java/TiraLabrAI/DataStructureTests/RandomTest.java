/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLabrAI.DataStructureTests;

import TiraLab.Structures.RandomGen;
import java.util.HashMap;
import java.util.Random;
import org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ColdFish
 */
public class RandomTest {

    private Random randomOrig;
    private RandomGen randomMy;

    public RandomTest() {
    }

    @Before
    public void initRandom() {
        randomOrig = new Random();
        randomMy = new RandomGen();
    }

    @Test
    public void randomIsRandomIsh() {
        double maxDeviationPercent = 0.025;

        HashMap<Integer, Integer> map = generateMap(150000, 2);
        assertTrue(diff(map) <= (150000 * maxDeviationPercent));

        map = generateMap(150000, 100);
        assertTrue(diff(map) <= (150000 * maxDeviationPercent));

        map = generateMap(10000000, 1500);
        assertTrue(diff(map) <= (10000000 * maxDeviationPercent));
    }

    public int diff(HashMap<Integer, Integer> map) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (Integer entry : map.values()) {
            if (entry > max) {
                max = entry;
            }
            if (entry < min) {
                min = entry;
            }
        }

        System.out.println("max : " + max + ", min: " + min);
        int diff = max - min;
        return diff;
    }

    public HashMap<Integer, Integer> generateMap(int rounds, int numbers) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numbers; i++) {
            map.put(i, 0);
        }

        for (int i = 0; i < rounds; i++) {
            int x = randomMy.getRandomInt(numbers);
            map.put(x, map.get(x) + 1);
        }

        return map;
    }
}

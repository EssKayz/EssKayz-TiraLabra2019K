/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLabrAI.DataStructureTests;

import TiraLab.Structures.ArrayLib;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;
import org.junit.*;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author ColdFish
 */
public class ArrayLibTest {

    private ArrayLib arrayLib;

    @Before
    public void init() {
        arrayLib = new ArrayLib();
    }

    @Test
    public void sortingWorks() {
        int strings = 10000;
        String[] arr = new String[strings];

        for (int i = 0; i < strings; i++) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int x = 0; x < targetStringLength; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();
            arr[i] = generatedString;
        }

        String[] clone = arr.clone();

        arrayLib.sort(arr, 0, arr.length - 1);
        Arrays.sort(clone);
        boolean equal = Arrays.equals(arr, clone);
        System.out.println("The arrays matched : " + equal);
        assertTrue(equal);
    }

    @Test
    public void sortingIsNotSuperSlow() {
        int strings = 1000000;
        String[] arr = new String[strings];

        for (int i = 0; i < strings; i++) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int x = 0; x < targetStringLength; x++) {
                int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();
            arr[i] = generatedString;
        }

        String[] clone = arr.clone();

        long startTime = System.currentTimeMillis();
        arrayLib.sort(arr, 0, arr.length - 1);
        long endTime = System.currentTimeMillis();
        long timeSpentMyAlgo = endTime - startTime;

        startTime = System.currentTimeMillis();
        Arrays.sort(clone);
        endTime = System.currentTimeMillis();
        long timeSpentArrays = endTime - startTime;

        System.out.println("TimeArrays : " + timeSpentArrays + ", mine : " + timeSpentMyAlgo);
        long diff = Math.abs(timeSpentArrays - timeSpentMyAlgo);
        System.out.println("Difference in milliseconds : " + diff);
        // pass the test if the difference is less then 25% or my algorithm is faster
        assertTrue(diff < timeSpentArrays * 0.25 || timeSpentMyAlgo < timeSpentArrays);
    }
}

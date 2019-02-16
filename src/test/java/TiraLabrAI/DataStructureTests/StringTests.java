/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiraLabrAI.DataStructureTests;

import TiraLab.Structures.StringMethods;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ColdFish
 */
public class StringTests {

    private StringMethods stringMeth;

    public StringTests() {
        stringMeth = new StringMethods();
    }

    @Test
    public void substringWorks() {
        String test = "4352345ksjgcvljlk4325435";

        for (int i = 0; i < test.length(); i++) {
            String control = test.substring(0, i);
            String mySub = stringMeth.substring(test, 0, i);
            assertTrue(control.equals(mySub));

            control = test.substring(i, test.length() - 1);
            mySub = stringMeth.substring(test, i, test.length() - 1);
            assertTrue(control.equals(mySub));
        }
    }

    @Test
    public void indexOfWorksCorrectly() {
        String test = "43ssfa52345ssfaksjgcvljlk43ssfa25435";
        String toFind = "52345ss";

        int realIndex = test.indexOf(toFind);
        int myIndex = stringMeth.indexOf(toFind, test);
        assertTrue(realIndex == myIndex);
    }

    @Test
    public void indexOfWorksCorrectlyWhenNoValueMatch() {
        String test = "43ssfa52345ssfaksjgcvljlk43ssfa25435";
        String toFind = "asdfasdf";

        int realIndex = test.indexOf(toFind);
        int myIndex = stringMeth.indexOf(toFind, test);
        assertTrue(realIndex == myIndex);
    }

    @Test
    public void lastIndexOfWorksCorrectly() {
        String test = "43ssfa52345ssfaksjgcvljlk43ssfa25435";
        String toFind = "ssfa";

        int realIndex = test.lastIndexOf(toFind);
        int myIndex = stringMeth.lastIndexOf(toFind, test);
        assertTrue(realIndex == myIndex);
    }

    @Test
    public void lastIndexOfWorksCorrectlyWhenNoMatch() {
        String test = "43ssfa52345ssfaksjgcvljlk43ssfa25435";
        String toFind = "asdfasdf";

        int realIndex = test.lastIndexOf(toFind);
        int myIndex = stringMeth.lastIndexOf(toFind, test);
        assertTrue(realIndex == myIndex);
    }

    @Test
    public void indexOfFromIndexWorks() {
        String test = "43ssfa52345ssfaksjgcvljlk43ssfa25435";
        String toFind = "sjgcv";

        int realIndex = test.indexOf(toFind, 5);
        int myIndex = stringMeth.indexOf(toFind, test, 5);
        assertTrue(realIndex == myIndex);
    }

}

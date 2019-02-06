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
public class StringMethods {

    public int lastIndexOf(String toFind, String fullstring) {
        int lastIndex = -1;
        for (int i = fullstring.length() - toFind.length(); i > 0; i--) {
            boolean found = true;
            for (int j = 0; j < toFind.length(); j++) {
                if (fullstring.charAt(i + j) == toFind.charAt(j)) {
                    continue;
                }
                found = false;
                break;
            }

            if (found) {
                return i;
            }
        }

        return lastIndex;
    }

    public int indexOf(String toFind, String fullstring, int startIndex) {
        String sub = substring(fullstring, startIndex, fullstring.length());
        return indexOf(toFind, sub) + startIndex;
    }

    public int indexOf(String toFind, String fullstring) {
        if (toFind.length() > fullstring.length()) {
            return -1;
        }
        for (int i = 0; i < fullstring.length(); i++) {
            boolean found = true;
            for (int j = 0; j < toFind.length(); j++) {
                if (fullstring.charAt(i + j) == toFind.charAt(j)) {
                    continue;
                }
                found = false;
                break;
            }
            if (found) {
                return i;
            }
        }
        return -1;
    }

    public String substring(String original, int start, int end) {
        String derp = "";
        for (int i = start; i < end; i++) {
            derp += (original.charAt(i));
        }

        return derp;
    }

}

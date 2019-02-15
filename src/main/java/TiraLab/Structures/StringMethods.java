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

        /**
     * Get the index of the last occurrence of a substring from another string
     * @param toFind the substring to be found
     * @param fullstring the full string to be searched from
     * @return the index of the first letter of the last occurrence of the substring
     */
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

    /**
     * Get the index of the first occurence of a substring from another string, starting from the parameter index
     *
     * @param toFind the substring to be found
     * @param fullstring the full string to be searched from
     * @param startIndex the index to start the search from
     * @return the index of the first character of the substring
     */
    public int indexOf(String toFind, String fullstring, int startIndex) {
        String sub = substring(fullstring, startIndex, fullstring.length());
        int index = indexOf(toFind, sub);
        if (index == -1) {
            return -1;
        }
        return index + startIndex;
    }

    /**
     * Get the index of the first occurrence of a substring from another string
     *
     * @param toFind the substring to be found
     * @param fullstring the full string to be searched from
     * @return the index of the first character of the substring
     */
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

    /**
     * Get a substring of a string
     *
     * @param original the original string in full length
     * @param start the index of first char of substring
     * @param end the index of the last char of the substring
     * @return the substring formed from start to end
     */
    public String substring(String original, int start, int end) {
        String derp = "";
        for (int i = start; i < end; i++) {
            derp += (original.charAt(i));
        }

        return derp;
    }

}

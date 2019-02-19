package ru.job4j.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for converting List of Integers
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {
    /**
     * Combines a list of arrays into one linear list.
     * @param list incoming list of int arrays
     * @return resulting list of Integers
     */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] arr : list) {
            for (int number : arr) {
                result.add(number);
            }
        }
        return result;
    }

    /**
     * Convert List of Integers to 2D Array
     * @param list List of Integers
     * @param rows number of rows in Array
     * @return 2D Array
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) (Math.ceil((double) list.size() / rows));
        int[][] array = new int[rows][cells];
        int row = 0;
        int cell = 0;
        for (Integer num : list) {
            array[row][cell] = num;
            cell++;
            if (cell == cells) {
                row++;
                cell = 0;
            }
        }
        /*
        Iterator<Integer> iterator = list.iterator();
        for (int[] arr : array) {
            for (int cell = 0; cell < arr.length; cell++) {
                if (iterator.hasNext()) {
                    arr[cell] = iterator.next();
                } else {
                    arr[cell] = 0;
                }
            }
        }*/
        return array;
    }
}

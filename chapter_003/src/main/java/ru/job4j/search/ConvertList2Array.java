package ru.job4j.search;

import java.util.Iterator;
import java.util.List;

/**
 * Class for converting List of Integers to Array
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {
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

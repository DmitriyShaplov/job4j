package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for converting Matrix to List of Integers
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arr : array) {
            for (int num : arr) {
                list.add(num);
            }
        }
        return list;
    }
}

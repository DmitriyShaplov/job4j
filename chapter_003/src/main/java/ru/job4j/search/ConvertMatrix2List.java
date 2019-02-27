package ru.job4j.search;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for converting Matrix to List of Integers
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream).boxed()
                .collect(Collectors.toList());
    }
}

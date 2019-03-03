package ru.job4j.streamapi;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shaplov
 * @since 03.03.2019
 */
public class ConvertMatrixToList {

    /**
     * convert List of List<Integer> to List<Integer>
     * @param list List<List<Integer>>
     * @return List<Integer>
     */
    public List<Integer> convert(List<List<Integer>> list) {
        return list.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}


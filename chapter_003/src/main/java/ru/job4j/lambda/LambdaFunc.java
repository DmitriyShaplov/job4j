package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class LambdaFunc {
    /**
     * Fills List with double values, calculated based on lambda function
     * @param start start num
     * @param end end num
     * @param function function
     * @return list of double
     */
    public List<Double> diapason(int start, int end, Function<Double, Double> function) {
        List<Double> list = new ArrayList<>();
        for (int index = start; index < end; index++) {
            list.add(function.apply((double) index));
        }
        return list;
    }
}

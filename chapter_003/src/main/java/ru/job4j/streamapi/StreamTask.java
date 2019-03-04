package ru.job4j.streamapi;

import java.util.Arrays;

/**
 * @author shaplov
 * @since 04.03.2019
 */
public class StreamTask {

    /**
     * Sums the square of even numbers.
     * @param numbers int array
     * @return sum
     */
    public int sumEvenSquares(int[] numbers) {
        return Arrays.stream(numbers).filter(v -> v % 2 == 0)
                .map(v -> (int) Math.pow(v, 2)).sum();

    }
}

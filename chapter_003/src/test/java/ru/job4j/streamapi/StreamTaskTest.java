package ru.job4j.streamapi;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StreamTaskTest {

    @Test
    public void whenCalculateEvenSumThenSum() {
        var summator = new StreamTask();
        var numbers = new int[] {1, 2, 4, 6, 7, 3, 5, 11, 10};
        var result = summator.sumEvenSquares(numbers);
        var except = 156;
        assertThat(result, is(except));
    }
}

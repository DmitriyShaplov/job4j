package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test FindLoop.
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class FindLoopTest {

    @Test
    public void whenArrayHasElement5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasElement10Then2() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 5, 10, 10, 11};
        int value = 10;
        int result = find.indexOf(input, value);
        int expect = 2;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayHasNotElmThenMinusOne() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 4;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }
}
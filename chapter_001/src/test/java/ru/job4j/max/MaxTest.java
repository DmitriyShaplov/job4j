package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for max.
 * @author shaplov
 * @version $Id$
 * @since 06.12.2018
 */
public class MaxTest {

    /**
     * Test
     * Второе число больше
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Test
     * Первое число больше
     */
    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(10, 2);
        assertThat(result, is(10));
    }

    /**
     * Тест на максимальное из трех чисел
     */
    @Test
    public void whenSecondGreaterOthers() {
        Max maxim = new Max();
        int result = maxim.max(4, 20, 2);
        assertThat(result, is(20));
    }
}

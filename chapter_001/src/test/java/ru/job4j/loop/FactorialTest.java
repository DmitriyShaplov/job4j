package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тест класса Factorial
 * @author shaplov
 * @version 1.00
 * @since 07.12.2018
 */
public class FactorialTest {

    /**
     * тест функции calc
     * с ненулевым n
     */
    @Test
    public void whenNIs5ThenFactorialIs120() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(5);
        assertThat(result, is(120));
    }

    /**
     * тест функции calc
     * с нулевым n
     */
    @Test
    public void whenNIsZeroThenFactorialIsOne() {
        Factorial factorial = new Factorial();
        int result = factorial.calc(0);
        assertThat(result, is(1));
    }
}

package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author shaplov
 * @version $Id$
 * @since 04.12.2018
 */
public class CalculatorTest {

    /**
     * Test add.
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test subtract.
     */
    @Test
    public void whenTenMinusSixThenFour() {
        Calculator calc = new Calculator();
        calc.subtract(10D, 6D);
        double result = calc.getResult();
        double expected = 4D;
        assertThat(result, is(expected));
    }

    /**
     * Test div.
     */
    @Test
    public void whenDivTenOnTwoThenFive() {
        Calculator calc = new Calculator();
        calc.div(10D, 2D);
        double result = calc.getResult();
        double expected = 5D;
        assertThat(result, is(expected));
    }

    /**
     * Test multiple.
     */
    @Test
    public void whenFourMultipleOnSixThen24() {
        Calculator calc = new Calculator();
        calc.multiple(4D, 6D);
        double result = calc.getResult();
        double expected = 24D;
        assertThat(result, is(expected));
    }
}

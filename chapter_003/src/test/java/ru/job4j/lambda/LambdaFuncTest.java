package ru.job4j.lambda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class LambdaFuncTest {
    @Test
    public void whenLinearFunctionThenLinearResults() {
        LambdaFunc function = new LambdaFunc();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        LambdaFunc function = new LambdaFunc();
        List<Double> result = function.diapason(2, 5, x -> Math.pow(x, 2) - 1);
        List<Double> expected = Arrays.asList(3D, 8D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenLogarithmicFunctionThenLogarithmicResults() {
        LambdaFunc function = new LambdaFunc();
        List<Double> result = function.diapason(100, 101, Math::log10);
        List<Double> expected = Arrays.asList(2D);
        assertThat(result, is(expected));
    }
}

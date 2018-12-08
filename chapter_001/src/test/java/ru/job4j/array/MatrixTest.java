package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.util.*;

/**
 * Matrix test.
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class MatrixTest {
    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(2);
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expect));
    }

    @Test
    public void when4on4() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(4);
        int[][] expect = {
                {1, 2, 3, 4},
                {2, 4, 6, 8},
                {3, 6, 9, 12},
                {4, 8, 12, 16}
        };
        assertThat(table, is(expect));
    }

    @Test
    public void whenSixMultiplySevenThen42() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiple(10);
        int result = table[6][5];
        int expect = 42;
        assertThat(result, is(expect));
    }
}

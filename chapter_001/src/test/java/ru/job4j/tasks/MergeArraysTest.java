package ru.job4j.tasks;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Тестируем слияние двух массивов
 * @author shaplov
 */
public class MergeArraysTest {

    @Test
    public void whenMergeTwoSortedArraysThenOneSortedArray() {
        MergeArrays arr = new MergeArrays();
        int[] first = {1, 4, 5, 5, 6, 10, 11};
        int[] second = {0, 1, 3, 7, 8, 9, 10};
        int[] expect = {0, 1, 1, 3, 4, 5, 5, 6, 7, 8, 9, 10, 10, 11};
        int[] result = arr.mergeFunc(first, second);
        assertThat(result, is(expect));
    }
}

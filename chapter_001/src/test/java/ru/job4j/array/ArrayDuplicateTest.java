package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        ArrayDuplicate arDup = new ArrayDuplicate();
        String[] input = new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"};
        String[] result = arDup.remove(input);
        String[] expect = new String[] {"Привет", "Мир", "Супер"};
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayWithRepeatedElementsThenArrayWithOneElement() {
        ArrayDuplicate arDup = new ArrayDuplicate();
        String[] input = new String[] {"Привет", "Привет", "Привет", "Привет", "Привет"};
        String[] result = arDup.remove(input);
        String[] expect = new String[] {"Привет"};
        assertThat(result, is(expect));
    }
}
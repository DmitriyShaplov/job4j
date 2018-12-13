package ru.job4j.tasks;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author shaplov
 * @since 13.12.18
 */
public class WordTest {

    @Test
    public void whenTwoStringsDifferByOnePermutationThenTrue() {
        Word word = new Word();
        String first = "забивака";
        String second = "звбиаака";
        boolean result = word.isOnlyOnePermutation(first, second);
        assertThat(result, is(true));
    }

    @Test
    public void whenTwoStringsDifferMoreThenOnePermutationThenFalse() {
        Word word = new Word();
        String first = "приветствие";
        String second = "превитствеи";
        boolean result = word.isOnlyOnePermutation(first, second);
        assertThat(result, is(false));
    }

    @Test
    public void whenTwoStringsIsEqualsThenFalse() {
        Word word = new Word();
        String first = "забивака";
        String second = "забивака";
        boolean result = word.isOnlyOnePermutation(first, second);
        assertThat(result, is(false));
    }

    @Test
    public void whenTwoStringsDifferByOneCharThenFalse() {
        Word word = new Word();
        String first = "приветствие";
        String second = "пруветствие";
        boolean result = word.isOnlyOnePermutation(first, second);
        assertThat(result, is(false));
    }
}

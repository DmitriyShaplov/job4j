package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author shaplov
 * @since 09.03.2019
 */
public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeAndDeleteElementThenUseGetZeroResultTwo() {
        list.delete();
        assertThat(list.get(0), is(2));
    }

    @Test
    public void whenAddThreeAndDeleteElementThenUseGetSizeResultTwo() {
        list.delete();
        assertThat(list.getSize(), is(2));
    }
}
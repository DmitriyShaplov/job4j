package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Class for testing Simple ArrayList.
 * @author shaplov
 * @since 09.03.2019
 */
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>(5);
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementThenUseGetSizeResultThree() {
        assertThat(list.size(), is(3));
    }

    @Test
    public void whenAddThreeElementThenGetIteratorAndConcatenateThenString123() {
        var it = list.iterator();
        StringBuilder result = new StringBuilder();
        while (it.hasNext()) {
            result.append(it.next());
        }
        assertThat(result.toString(), is("123"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddThreeElementThenGetIteratorAndAddAnotherElementThenException() {
        var it = list.iterator();
        list.add(4);
        it.next();
    }

    @Test
    public void whenCreateArraySizeFiveThenAddSixElementsAndGetLastResultIsLast() {
        list.add(4);
        list.add(5);
        list.add(6);
        assertThat(list.get(5), is(6));
    }
}
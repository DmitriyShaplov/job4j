package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author shaplov
 * @since 07.03.2019
 */
public class SimpleListTest {

    @Test
    public void whenAddAndGetString() {
        SimpleList<String> list = new SimpleList<>(5);
        list.add("1");
        list.add("2");
        String result = list.get(1);
        assertThat(result, is("2"));
    }

    @Test
    public void whenSetThenChanges() {
        SimpleList<Integer> list = new SimpleList<>(5);
        list.add(1);
        list.add(2);
        list.set(0, 5);
        int result = list.get(0);
        assertThat(result, is(5));
    }

    @Test
    public void whenUseIterator() {
        SimpleList<String> list = new SimpleList<>(5);
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> it = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        assertThat(sb.toString(), is("123"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddAndOutOfBounds() {
        SimpleList<Integer> list = new SimpleList<>(1);
        list.add(1);
        list.add(2);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetOutOfBounds() {
        SimpleList<Integer> list = new SimpleList<>(5);
        list.add(0);
        list.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorHasNotNextElement() {
        SimpleList<Integer> list = new SimpleList<>(1);
        Iterator it = list.iterator();
        it.next();
    }
}
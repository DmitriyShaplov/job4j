package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test for base LinkedList.
 * @author shaplov
 * @since 09.03.2019
 */
public class BaseLinkedListTest {

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        BaseLinkedList<Integer> list = new BaseLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(1), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorThenNextResultNSEE() {
        BaseLinkedList<Integer> list = new BaseLinkedList<>();
        var it = list.iterator();
        it.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddElementThenGetIteratorAndAddAnotherElementResultCME() {
        BaseLinkedList<Integer> list = new BaseLinkedList<>();
        var it = list.iterator();
        list.add(1);
        it.next();
    }

    @Test
    public void whenAddThreeElementsThenGetIteratorAndConcatenateElementsThen123() {
        BaseLinkedList<Integer> list = new BaseLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        var it = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        assertThat(sb.toString(), is("123"));
    }
}
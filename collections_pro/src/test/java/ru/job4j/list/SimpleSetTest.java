package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void whenAddUniqueStringThenListContainsThisValue() {
        SimpleSet<String> set = new SimpleSet<>(5);
        set.add("1");
        set.add("2");
        var it = set.iterator();
        List<String> list = new ArrayList<>();
        while (it.hasNext()) {
            list.add(it.next());
        }
        var result = list.contains("1") && list.contains("2");
        assertThat(set.size(), is(2));
        assertThat(result, is(true));
    }

    @Test
    public void whenAddNotUniqueElementThenListSizeDoesntGrow() {
        SimpleSet<String> set = new SimpleSet<>(5);
        set.add("1");
        set.add("2");
        set.add("2");
        assertThat(set.size(), is(2));
    }
}
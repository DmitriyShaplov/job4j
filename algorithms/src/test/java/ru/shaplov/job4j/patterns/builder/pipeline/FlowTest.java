package ru.shaplov.job4j.patterns.builder.pipeline;

import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class FlowTest {

    @Test
    public void testFilterAndReduce() {
        Integer reduce = Flow.of(1, 2, 3, 4, 5)
                .filter(v -> v < 5)
                .filter(v -> v > 2)
                .reduce(Integer::sum);
        assertThat(reduce, is(7));
    }

    @Test
    public void testFilterAndCollect() {
        Collection<Integer> collect = Flow.of(1, 2, 3, 4, 5)
                .filter(v -> v < 5)
                .filter(v -> v > 2)
                .collect(LinkedList::new);
        List<Integer> integers = List.of(3, 4);
        assertEquals(collect, integers);
        assertThat(collect, instanceOf(LinkedList.class));
    }
}
package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleQueueTest {

    @Test
    public void whenAddThreeElements123ThenPullResult123() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.pull(), is(1));
        assertThat(queue.pull(), is(2));
        assertThat(queue.pull(), is(3));
    }

    @Test
    public void whenAddThreeElements123ThenPullTwoThenAddOne4AndPullAnotherTwoResult1234() {
        SimpleQueue<Integer> queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.pull(), is(1));
        assertThat(queue.pull(), is(2));
        queue.push(4);
        assertThat(queue.pull(), is(3));
        assertThat(queue.pull(), is(4));
    }
}
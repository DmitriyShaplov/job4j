package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Test for stack
 * @author shaplov
 * @since 09.03.2019
 */
public class SimpleStackTest {

    @Test
    public void whenPushThreeElements123ThenPollResult321() {
        SimpleStack<Integer> stack = new SimpleStackClass<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }
}
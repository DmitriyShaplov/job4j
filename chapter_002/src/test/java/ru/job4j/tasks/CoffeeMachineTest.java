package ru.job4j.tasks;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @authro shaplov
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachineTest {

    @Test
    public void whenInsertBanknoteThenReceiveChange() {
        int[] expect = {10, 10, 10, 10, 10, 10, 5, 2, 2};
        int[] result = CoffeeMachine.changes(100, 31);
        assertThat(result, is(expect));
    }

    @Test
    public void whenInsertCoinThenReceiveChange() {
        int[] expect = {5, 2, 1};
        int[] result = CoffeeMachine.changes(10, 2);
        assertThat(result, is(expect));
    }

    @Test
    public void whenInsertNotAMoneyThenReceiveNothing() {
        int[] expect = {};
        int[] result = CoffeeMachine.changes(11, 2);
        assertThat(result, is(expect));
    }

    @Test
    public void whenInsertLessThenPriceThenReceiveNothing() {
        int[] expect = {};
        int[] result = CoffeeMachine.changes(50, 100);
        assertThat(result, is(expect));
    }
}

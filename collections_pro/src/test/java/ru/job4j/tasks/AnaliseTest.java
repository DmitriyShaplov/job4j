package ru.job4j.tasks;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

/**
 * Test for Analise class.
 * @author shaplov
 * @since 15.03.2019
 */
public class AnaliseTest {

    @Test
    public void whenFiveUsersThenAddTreeDeleteTwoAndChangeOneResult321() {
        Analise analise = new Analise();
        var info = analise.diff(
                List.of(
                        new Analise.User(1, "test1"),
                        new Analise.User(2, "test2"),
                        new Analise.User(3, "test3"),
                        new Analise.User(4, "test4"),
                        new Analise.User(5, "test5")
                ),
                List.of(
                        new Analise.User(1, "test1"),
                        new Analise.User(4, "test44"),
                        new Analise.User(5, "test5"),
                        new Analise.User(6, "test2"),
                        new Analise.User(7, "test3"),
                        new Analise.User(8, "test4")
                )
        );
        int[] result = {info.getAdded(), info.getDeleted(), info.getChanged()};
        int[] expect = {3, 2, 1};
        assertThat(result, is(expect));
    }
}
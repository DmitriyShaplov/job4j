package ru.job4j.tasks;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RobotTest {

    @Test
    public void whenCalculatesMinWayThen7() {
        int[][] array = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        int way = new Robot().minWay(array, 3, 1, 0, 3);
        assertThat(way, is(7));
    }

    @Test
    public void whenCalculatesMinWayThen9() {
        int[][] array = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        int way = new Robot().minWay(array, 3, 1, 0, 3);
        assertThat(way, is(9));
    }

    @Test
    public void whenCalculatesMinWayThen5() {
        int[][] array = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        int way = new Robot().minWay(array, 3, 1, 0, 3);
        assertThat(way, is(5));
    }

    @Test
    public void whenCalculatesMinWayThenMinus1() {
        int[][] array = new int[][]{
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {1, 1, 1, 0, 1}
        };
        int way = new Robot().minWay(array, 3, 1, 0, 3);
        assertThat(way, is(-1));
    }
}
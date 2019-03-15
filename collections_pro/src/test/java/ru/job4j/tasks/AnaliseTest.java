package ru.job4j.tasks;

import org.junit.Test;

import java.util.ArrayList;
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
        List<Analise.User> listPrv = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            listPrv.add(new Analise.User(i, String.format("test%d", i)));
        }
        var listCur = new ArrayList<>(listPrv);
        for (int i = 7000; i < 10000; i++) {
            listCur.set(i, new Analise.User(i + 3000, String.format("test%d", i + 3000)));
        }
        for (int i = 5000; i < 7000; i++) {
            listCur.set(i, new Analise.User(i, String.format("test%d", i + 3000)));
        }
        for (int i = 0; i < 500; i++) {
            listCur.remove(0);
        }
        var time = System.currentTimeMillis();
        var info = analise.diff(listPrv, listCur);
        System.out.println(System.currentTimeMillis() - time);
        int[] result = {info.getAdded(), info.getDeleted(), info.getChanged()};
        int[] expect = {3000, 3500, 2000};
        assertThat(result, is(expect));
    }
}
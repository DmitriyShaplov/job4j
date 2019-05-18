package ru.job4j.synchronize.safelist;

import org.junit.Test;
import ru.job4j.list.SimpleArrayList;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SafeListTest {

    private class ThreadList extends Thread {
        private final SafeList<Integer> list;

        private ThreadList(SafeList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(3);
        }
    }

    @Test
    public void whenUseIteratorAndOtherThreadAddsNewValueResultIteratorWithOldValues() throws InterruptedException {
        SafeList<Integer> list = new SafeList<>(new SimpleArrayList<>());
        list.add(1);
        list.add(2);
        Thread thread = new ThreadList(list);
        Iterator<Integer> iterator = list.iterator();
        thread.start();
        Thread.sleep(1000);
        var except = Set.of(1, 2);
        var result = new HashSet<Integer>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertThat(result, is(except));
        assertThat(list.size(), is(3));
        assertThat(list.get(2), is(3));
    }
}
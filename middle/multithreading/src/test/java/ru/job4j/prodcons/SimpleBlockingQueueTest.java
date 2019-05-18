package ru.job4j.prodcons;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shaplov
 * @since 18.05.2019
 */
public class SimpleBlockingQueueTest {

    private static class ProducerThread extends Thread {
        private final SimpleBlockingQueue<Integer> queue;

        private ProducerThread(final SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class ConsumerThread extends Thread {
        private final SimpleBlockingQueue<Integer> queue;
        private final List<Integer> list;

        private ConsumerThread(final SimpleBlockingQueue<Integer> queue, final List<Integer> list) {
            this.queue = queue;
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    list.add(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void whenCreateQueueWith3ThresholdThenRunProducerAndConsumerResultListContains0to9Numbers()
            throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        List<Integer> list = new LinkedList<>();
        Thread consumer = new ConsumerThread(queue, list);
        Thread producer = new ProducerThread(queue);
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        List<Integer> expect = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertThat(list, is(expect));
    }
}
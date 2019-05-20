package ru.job4j.nonblocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Class for blocking queue.
 * @author shaplov
 * @since 18.05.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    /**
     * Maximum capacity.
     */
    private final int threshold;
    /**
     * Flag for empty state.
     */
    private boolean empty = true;
    /**
     * Flag for overflowing state.
     */
    private boolean overflow = false;
    /**
     * Constructor with threshold parameter.
     * @param threshold threshold of queue.
     */
    public SimpleBlockingQueue(final int threshold) {
        this.threshold = threshold;
    }

    /**
     * Default constructor with 10 threshold.
     */
    public SimpleBlockingQueue() {
        this.threshold = 10;
    }

    /**
     * Offers value in queue
     * or waiting for poll.
     * @param value T value.
     */
    public synchronized void offer(T value) throws InterruptedException {
        if (queue.size() == threshold) {
            overflow = true;
        }
        while (overflow) {
            this.wait();
        }
        queue.offer(value);
        empty = false;
        this.notify();
    }
    /**
     * Polls value from queue
     * or waiting for offer.
     * @return value.
     */
    public synchronized T poll() throws InterruptedException {
        if (queue.isEmpty()) {
            empty = true;
        }
        while (empty) {
            this.wait();
        }
        overflow = false;
        this.notify();
        return queue.poll();
    }
}
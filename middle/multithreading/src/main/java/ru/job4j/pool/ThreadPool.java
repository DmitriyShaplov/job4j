package ru.job4j.pool;

import ru.job4j.prodcons.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shaplov
 * @since 21.05.2019
 */
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void init() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int index = 0; index < size; index++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll().run();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            threads.add(thread);
            thread.start();
        }
    }

    public void shutdown() {
        threads.forEach(
                v -> {
                    v.interrupt();
                    try {
                        v.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        Thread producer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            pool.work(
                                    () -> System.out.println("Thread is working: " + Thread.currentThread().getName())
                            );
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        producer.start();
        pool.init();
        Thread.sleep(5000);
        producer.interrupt();
        pool.shutdown();
    }
}

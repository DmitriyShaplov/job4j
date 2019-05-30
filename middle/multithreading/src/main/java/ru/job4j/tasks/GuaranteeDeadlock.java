package ru.job4j.tasks;

import java.util.concurrent.CountDownLatch;

/**
 * @author shaplov
 * @since 30.05.2019
 */
public class GuaranteeDeadlock {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch first = new CountDownLatch(1);
        CountDownLatch second = new CountDownLatch(1);
        Thread t1 = new Thread(
                () -> {
                    try {
                        second.await();
                        System.out.println("first done");
                        first.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        first.await();
                        System.out.println("second done");
                        second.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

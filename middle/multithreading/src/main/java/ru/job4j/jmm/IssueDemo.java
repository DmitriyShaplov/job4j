package ru.job4j.jmm;

/**
 * @author shaplov
 * @since 14.05.2019
 */
public class IssueDemo {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        Thread t1 = new Thread(new Counter(data));
        Thread t2 = new Thread(new Reader(data));
        t1.start();
        t2.start();
    }
}


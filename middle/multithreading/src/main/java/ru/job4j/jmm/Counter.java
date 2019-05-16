package ru.job4j.jmm;

/**
 * @author shaplov
 * @since 14.05.2019
 */
public class Counter implements Runnable {

    private Data data;

    public Counter(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            ++data.count;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

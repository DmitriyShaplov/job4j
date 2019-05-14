package ru.job4j.jmm;

/**
 * @author shaplov
 * @since 14.05.2019
 */
public class Reader implements Runnable {
    private Data data;

    public Reader(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            if (data.count % 2 == 0) {
                System.out.println("Reader: " + data.count);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package ru.job4j.wget;

import java.io.IOException;

/**
 * @author shaplov
 * @since 27.05.2019
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            return;
        }
        WGet wget = new WGet();
        Thread counter = new Thread( () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000);
                    wget.reset();
                    wget.checkPermission();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        Thread downloader = new Thread(
                () -> {
                    try {
                        wget.download(args[0], args[1]);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        counter.setDaemon(true);
        counter.start();
        downloader.start();
        downloader.join();
    }
}

package ru.job4j.mailing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shaplov
 * @since 22.05.2019
 */
public class EmailNotification {

    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        final String subject = String.format("Notification %s to email %s", user.username(), user.email());
        final String body = String.format("Add a new event to %s", user.username());
        pool.submit(
                () -> send(subject, body, user.email())
        );
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String subject, String body, String email) {
        System.out.println(String.format("%s%n%s%n%s%n", subject, body, Thread.currentThread().getName()));
    }

    public static void main(String[] args) {
        EmailNotification emailer = new EmailNotification();
        for (int i = 0; i < 10; i++) {
            User user = new User(String.format("test%d", i), String.format("test@mail.%d", i));
            emailer.emailTo(user);
        }
        emailer.close();
    }
}

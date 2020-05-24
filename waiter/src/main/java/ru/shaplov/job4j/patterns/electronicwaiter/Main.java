package ru.shaplov.job4j.patterns.electronicwaiter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author shaplov
 * @since 22.02.2020
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext();
        context.scan("ru.shaplov.job4j.patterns.electronicwaiter");
        context.refresh();
        Waiter waiter = context.getBean(Waiter.class);
        waiter.start();
        User user = new User("Посетитель 1");
        waiter.startInteract(user);
    }
}

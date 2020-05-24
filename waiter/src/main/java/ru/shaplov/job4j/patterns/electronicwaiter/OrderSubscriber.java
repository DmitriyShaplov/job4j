package ru.shaplov.job4j.patterns.electronicwaiter;

public interface OrderSubscriber {
    default void reactOnOrderIsReady(Order order) {};
    default void reactOnOrderAccepted(Order order) {};
}

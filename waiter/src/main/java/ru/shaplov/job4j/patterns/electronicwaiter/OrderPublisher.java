package ru.shaplov.job4j.patterns.electronicwaiter;

public interface OrderPublisher {
    void subscribe(Order order, OrderSubscriber subscriber);
    void unsubscribe(Order order, OrderSubscriber subscriber);
}

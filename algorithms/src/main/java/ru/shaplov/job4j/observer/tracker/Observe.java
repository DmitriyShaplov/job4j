package ru.shaplov.job4j.observer.tracker;

/**
 * @author shaplov
 * @since 07.12.2019
 */
public interface Observe<T> {
    void subscribe(T client);
    void unsubscribe(T client);
}

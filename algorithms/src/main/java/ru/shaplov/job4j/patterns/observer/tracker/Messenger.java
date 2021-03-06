package ru.shaplov.job4j.patterns.observer.tracker;

/**
 * @author shaplov
 * @since 07.12.2019
 */
public interface Messenger<T> {
    void send(T event);
}

package ru.job4j.list;

public interface SimpleQueue<E> {

    /**
     * Add last element.
     */
    void push(E value);

    /**
     * Get and delete first element.
     */
    E pull();
}

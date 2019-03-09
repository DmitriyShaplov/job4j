package ru.job4j.list;

/**
 * @author shaplov
 * @since 09.03.2019
 */
public interface SimpleStack<E> {

    /**
     * @return last pushed element.
     */
    E pull();

    /**
     * push element to stack.
     */
    void push(E value);
}

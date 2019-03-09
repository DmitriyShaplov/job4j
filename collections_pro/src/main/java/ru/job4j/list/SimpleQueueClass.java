package ru.job4j.list;

/**
 * @author shaplov
 * @since 09.03.2019
 */
public class SimpleQueueClass<E> extends BaseLinkedList<E>
        implements SimpleQueue<E> {

    @Override
    public void push(E value) {
        add(value);
    }

    @Override
    public E pull() {
        E result = getFirst();
        deleteFirst();
        return result;
    }
}

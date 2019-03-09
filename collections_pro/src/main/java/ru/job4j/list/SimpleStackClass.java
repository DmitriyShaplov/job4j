package ru.job4j.list;

public class SimpleStackClass<E> extends BaseLinkedList<E> implements SimpleStack<E>{

    /**
     * @return last pushed element.
     */
    @Override
    public E poll() {
        E result = getFirst();
        deleteFirst();
        return result;
    }

    /**
     * push element.
     */
    @Override
    public void push(E value) {
        linkFirst(value);
    }
}

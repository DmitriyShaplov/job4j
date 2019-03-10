package ru.job4j.list;

public class SimpleStackClass<E> implements SimpleStack<E> {

    private BaseLinkedList<E> linkList;

    public SimpleStackClass() {
        this.linkList = new BaseLinkedList<>();
    }

    /**
     * @return last pushed element.
     */
    @Override
    public E pull() {
        return this.linkList.deleteFirst();
    }

    /**
     * push element.
     */
    @Override
    public void push(E value) {
        this.linkList.linkFirst(value);
    }
}

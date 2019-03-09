package ru.job4j.list;

/**
 * Class for realization of simple LinkedList.
 * @author shaplov
 * @since 09.03.2019
 */
public class SimpleLinkedList<E> {

    private int size;
    private Node<E> first;

    /**
     * Adds element in HEAD.
     */
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = this.first;
        this.first = newNode;
        this.size++;
    }

    /**
     * Deletes head element.
     * @return deleted element
     */
    public E delete() {
        E result = this.first.data;
        this.first = this.first.next;
        this.size--;
        return result;
    }

    /**
     * Gets an item by index.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Gets size of collection.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Class for data storage.
     * @param <E>
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}

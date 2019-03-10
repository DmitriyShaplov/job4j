package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Base realization of LinkedList
 * @author shaplov
 * @since 09.03.2019
 */
public class BaseLinkedList<E> implements Iterable<E> {

    private Node<E> first;
    private Node<E> last;

    private int size = 0;
    private int modCount = 0;

    /**
     * Add element
     */
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (this.first == null) {
            this.first = newNode;
            this.last = newNode;
        } else {
            this.last.next = newNode;
            this.last = newNode;
        }
        modCount++;
        size++;
    }

    /**
     * delete first element
     */
    protected E deleteFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
        E result = this.first.data;
        this.first = this.first.next;
        return result;
    }

    /**
     * get first element
     */
    protected E getFirst() {
        return this.first.data;
    }

    protected void linkFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = this.first;
        this.first = newNode;
    }

    /**
     * Get size
     */
    public int size() {
        return this.size;
    }

    /**
     * Get element by index.
     */
    public E get(int index) {
        if (index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curNode = this.first;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode.data;
    }

    /**
     * iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int expectedModCount = BaseLinkedList.this.modCount;
            private Node<E> curNode = BaseLinkedList.this.first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != BaseLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                return curNode != null;
            }

            @Override
            public E next() {
                if (expectedModCount != BaseLinkedList.this.modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E data = curNode.data;
                curNode = curNode.next;
                return data;
            }
        };
    }

    /**
     * Node for storage information.
     */
    private static class Node<E> {

        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
        }
    }
}

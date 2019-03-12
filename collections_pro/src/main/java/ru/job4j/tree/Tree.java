package ru.job4j.tree;

import java.util.*;

/**
 * Simple Tree based on LinkedList.
 * @author shaplov
 * @since 12.03.2019
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private Node<E> root;
    private int modCount = 0;

    public Tree(E value) {
        this.root = new Node<>(value);
    }

    /**
     * Adds child to parent only if child
     * doesn't exists and parent is.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rst = false;
        Optional<Node<E>> pn;
        pn = findBy(parent);
        if (pn.isPresent()
                && findBy(child).isEmpty()) {
            rst = true;
            pn.get().add(new Node<>(child));
        }
        return rst;
    }

    /**
     * finds element in tree.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Returns is this Tree is binary.
     */
    public boolean isBinary() {
        boolean rst = false;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.leaves().size() >= 2) {
                rst = true;
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rst;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIter();
    }

    /**
     * Tree Iterator.
     */
    private class TreeIter implements Iterator<E> {

        private final Queue<Node<E>> nodes = new LinkedList<>();

        private final int expectedModCount;

        public TreeIter() {
            expectedModCount = modCount;
            fillNodes();
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return nodes.peek() != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return nodes.poll().getValue();
        }

        private void fillNodes() {
            Queue<Node<E>> data = new LinkedList<>();
            data.offer(root);
            while (!data.isEmpty()) {
                Node<E> el = data.poll();
                nodes.add(el);
                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
            }
        }
    }
}

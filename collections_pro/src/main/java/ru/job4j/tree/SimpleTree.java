package ru.job4j.tree;

import java.util.Optional;

public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    /**
     * Adds element child in parent.
     * Parent can have list of child.
     * @param parent parent
     * @param child child
     * @return result
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}

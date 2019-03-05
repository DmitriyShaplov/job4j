package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author shaplov
 * @since 05.03.2019
 */
public class Converter {

    /**
     *  Gets Iterator<Integer>
     *  from Iterator<Iterator<Integer>>.
     * @param it Iterator of Iterators
     * @return Iterator of Integers
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> inner = it.hasNext() ? it.next() : null;

            @Override
            public boolean hasNext() {
                if (inner == null) {
                    return false;
                }
                boolean result = false;
                if (inner.hasNext()) {
                    result = true;
                } else {
                    if (it.hasNext()) {
                        inner = it.next();
                        return hasNext();
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (inner == null) {
                    throw new NoSuchElementException();
                }
                if (inner.hasNext()) {
                    return inner.next();
                } else {
                    if (it.hasNext()) {
                        inner = it.next();
                        if (inner.hasNext()) {
                            return inner.next();
                        } else {
                            return next();
                        }
                    } else {
                        throw new NoSuchElementException();
                    }
                }
            }
        };
    }
}

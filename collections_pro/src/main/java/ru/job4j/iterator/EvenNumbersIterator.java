package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author shaplov
 * @since 05.03.2019
 */
public class EvenNumbersIterator implements Iterator {

    private final int[] values;
    private int index = -1;

    public EvenNumbersIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > nextEvenIndex();
    }

    @Override
    public Object next() {
        index = nextEvenIndex();
        if (index >= values.length) {
            throw new NoSuchElementException();
        }
        return values[index];
    }

    /**
     * finds index of next even number
     * @return index of even number or
     * index out of array bounds
     */
    private int nextEvenIndex() {
        int i = index + 1;
        boolean result = false;
        for (; i < values.length; i++) {
            if (values[i] % 2 == 0) {
                break;
            }
        }
        return i;
    }
}

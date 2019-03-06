package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author shaplov
 * @since 05.03.2019
 */
public class EvenNumbersIterator implements Iterator {

    private final int[] values;
    private int index = 0;

    public EvenNumbersIterator(final int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        for (; index < values.length; index++) {
            if (values[index] % 2 == 0) {
                break;
            }
        }
        return values.length > index;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var result = values[index];
        index++;
        return result;
    }
}

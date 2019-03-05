package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author shaplov
 * @since 05.03.2019
 */
public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int index1D = 0;
    private int index2D = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return values.length > index1D && values[index1D].length > index2D;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        var result = values[index1D][index2D];
        if (index2D < values[index1D].length - 1) {
            index2D++;
        } else {
            if (index1D < values.length - 1) {
                index1D++;
                index2D = 0;
            } else {
                index1D++;
            }
        }
        return result;
    }
}

package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for SimpleList with param
 * @author shaplov
 * @since 07.03.2019
 * @param <T>
 */
public class SimpleList<T> implements Iterable<T> {

    private Object[] objects;
    private int index = 0;

    /**
     * Constructor
     * @param size array size
     */
    public SimpleList(int size) {
        this.objects = new Object[size];
    }

    public void add(T model) {
        this.objects[index++] = model;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.objects[index];
    }

    public void set(int index, T model) {
        if (index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.objects[index] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return SimpleList.this.index > position;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) SimpleList.this.objects[position++];
            }
        };
    }
}

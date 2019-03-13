package ru.job4j.list;

import ru.job4j.generic.SimpleList;

import java.util.Iterator;

/**
 * Simple realization of Set based on SimpleList.
 * @author shaplov
 * @since 11.03.2019
 */
public class SimpleSet<T> implements Iterable<T> {

    private SimpleList<T> simpleList;

    public SimpleSet(int size) {
        this.simpleList = new SimpleList<>(size);
    }

    /**
     * Add unique value.
     * @return boolean result
     */
    public boolean add(T value) {
        if (!contains(value)) {
            simpleList.add(value);
            return true;
        }
        return false;
    }

    /**
     * Checks if this element is in the set.
     */
    public boolean contains(Object o) {
        boolean result = false;
        var it = simpleList.iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    result = true;
                    break;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public int size() {
        return simpleList.size();
    }

    @Override
    public Iterator<T> iterator() {
        return simpleList.iterator();
    }
}

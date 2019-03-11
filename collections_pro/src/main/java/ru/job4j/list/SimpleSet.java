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
        boolean result = true;
        var it = simpleList.iterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                result = false;
                break;
            }
        }
        if (result) {
            simpleList.add(value);
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

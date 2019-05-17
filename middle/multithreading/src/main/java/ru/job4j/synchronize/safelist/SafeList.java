package ru.job4j.synchronize.safelist;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

/**
 * @author shaplov
 * @since 17.05.2019
 */
@ThreadSafe
public class SafeList<E> implements Iterable<E> {

    @GuardedBy("this")
    private final SimpleArrayList<E> array;

    public SafeList(SimpleArrayList<E> array) {
        this.array = array;
    }

    public synchronized void add(E value) {
        array.add(value);
    }

    public synchronized E get(int index) {
        return array.get(index);
    }

    public synchronized int size() {
        return array.size();
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return copy(array).iterator();
    }

    /**
     * Copies this.array.
     * @param array SimpleArrayList.
     * @return copy of this.array.
     */
    private synchronized SimpleArrayList<E> copy(SimpleArrayList<E> array) {
        SimpleArrayList<E> listCopy = new SimpleArrayList<>();
        array.forEach(listCopy::add);
        return listCopy;
    }
}

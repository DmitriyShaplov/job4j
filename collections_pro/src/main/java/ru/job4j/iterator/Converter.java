package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
        return new Iterator<Integer>() {
            private List<Iterator<Integer>> iterators = new ArrayList<>();
            private int index = 0;

            {
                fillList();
            }

            private void fillList() {
                while (it.hasNext()) {
                    this.iterators.add(it.next());
                }
            }

            @Override
            public boolean hasNext() {
                return iterators.size() > index && iterators.get(index).hasNext();
            }

            @Override
            public Integer next() {
                if (index >= iterators.size()) {
                    throw new NoSuchElementException();
                }
                if (iterators.get(index).hasNext()) {
                    var result = iterators.get(index).next();
                    if (!iterators.get(index).hasNext()) {
                        index++;
                    }
                    return result;
                } else {
                    index++;
                    return next();
                }
            }
        };
    }
}

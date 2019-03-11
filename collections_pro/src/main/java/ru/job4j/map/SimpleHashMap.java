package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Simple realization of HashMap
 * @author shaplov
 * @since 11.03.2019
 */
public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Pair<K, V>> {

    private Pair<K, V>[] table;

    private int size = 0;
    private int modCount = 0;
    private int capacity = 16;

    private final static double loadFactor = 0.75;

    public SimpleHashMap() {
        resize();
    }

    public SimpleHashMap(int capacity) {
        this.capacity = capacity;
        resize();
    }

    /**
     * @return key-value iterator
     */
    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new PairIterator<>();
    }

    /**
     * Class for Pair Iterator.
     */
    private class PairIterator<K, V> implements Iterator<Pair<K, V>> {

        private int index;
        private int expectedModCount;

        public PairIterator() {
            setNextIndex();
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return this.index < table.length;
        }

        @Override
        @SuppressWarnings({"rawtypes","unchecked"})
        public Pair<K, V> next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Pair<K, V> pair = (Pair<K, V>) table[this.index];
            this.index++;
            setNextIndex();
            return pair;
        }

        /**
         * find next not null index.
         */
        private void setNextIndex() {
            while (this.index < table.length && table[this.index] == null) {
                this.index++;
            }
        }
    }

    /**
     * key-value pair class
     * @param <K> key
     * @param <V> value
     */
    public static class Pair<K, V> {

        private int hash;
        private K key;
        private V value;

        public Pair(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public V getValue() {
            return value;
        }
    }

    /**
     * Add pair key-value
     * @param key the key
     * @param value the value
     * @return result
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        int hash = hash(key);
        int index = index(hash);
        if (this.table[index] == null) {
            this.table[index] = new Pair<>(hash, key, value);
            this.modCount++;
            this.size++;
            if (size >= this.table.length * loadFactor) {
                resize();
            }
            result = true;
        }
        return result;
    }

    /**
     * Get value by key.
     * @param key the key
     * @return value
     */
    public V get(K key) {
        V result = null;
        int hash = hash(key);
        int index = index(hash);
        if (checkElement(key, index, hash)) {
            result = this.table[index].value;
        }
        return result;
    }

    /**
     * Delete value by key.
     * @param key the key
     * @return deleted value
     */
    public boolean delete(K key) {
        boolean result = false;
        int hash = hash(key);
        int index = index(hash);
        if (checkElement(key, index, hash)) {
            this.table[index] = null;
            result = true;
            modCount++;
            size--;
        }
        return result;
    }

    /**
     * Get hash by key.
     */
    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode();
    }

    /**
     * Get index by hash.
     */
    private int index(int hash) {
        return (this.table.length - 1) & hash;
    }

    /**
     * Check element exists.
     * @param key the key
     * @param index table index
     * @param hash the hash
     * @return existence
     */
    private boolean checkElement(K key, int index, int hash) {
        return this.table[index] != null
                && (this.table[index].hash == hash)
                && (this.table[index].key == key || this.table[index].key.equals(key));
    }

    /**
     * @return size of table key-value
     */
    public int size() {
        return this.size;
    }

    @SuppressWarnings({"rawtypes","unchecked"})
    private void resize() {
        if (this.table == null) {
            this.table = (Pair<K, V>[]) new Pair[this.capacity];
        } else {
            int newCap = this.table.length * 2;
            Pair<K, V>[] newTab = (Pair<K, V>[]) new Pair[newCap];
            System.arraycopy(table, 0, newTab, 0, this.table.length);
            this.table = newTab;
        }
    }
}

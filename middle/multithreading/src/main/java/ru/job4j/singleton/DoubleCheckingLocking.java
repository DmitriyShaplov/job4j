package ru.job4j.singleton;

import ru.job4j.tracker.Item;

/**
 * Lazy.
 * NON-Recommended. Lower performance.
 * @author shaplov
 * @since 21.05.2019
 */
public class DoubleCheckingLocking {

    private static volatile DoubleCheckingLocking instance;

    public DoubleCheckingLocking() {
    }

    public static DoubleCheckingLocking getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckingLocking.class) {
                if (instance == null) {
                    instance = new DoubleCheckingLocking();
                }
            }
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        DoubleCheckingLocking tracker = DoubleCheckingLocking.getInstance();
    }
}

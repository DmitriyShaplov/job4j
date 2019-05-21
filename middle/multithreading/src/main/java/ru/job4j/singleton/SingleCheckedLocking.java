package ru.job4j.singleton;

import ru.job4j.tracker.Item;

/**
 * Lazy.
 * NON-Recommended. Initialization inner critical section.\
 * Lower performance.
 * @author shaplov
 * @since 21.05.2019
 */
public class SingleCheckedLocking {

    private static SingleCheckedLocking instance;

    public SingleCheckedLocking() {
    }

    public static synchronized SingleCheckedLocking getInstance() {
        if (instance == null) {
            instance = new SingleCheckedLocking();
        }
        return instance;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        SingleCheckedLocking tracker = SingleCheckedLocking.getInstance();
    }
}

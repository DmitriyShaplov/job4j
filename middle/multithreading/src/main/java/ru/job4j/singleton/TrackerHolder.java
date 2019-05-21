package ru.job4j.singleton;

/**
 * Lazy.
 * Recommended in multithreading system.
 * @author shaplov
 * @since 21.05.2019
 */
public class TrackerHolder {

    public TrackerHolder() {
    }

    public static TrackerHolder getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerHolder INSTANCE = new TrackerHolder();
    }

    public static void main(String[] args) {
        TrackerHolder tracker = TrackerHolder.getInstance();
    }
}

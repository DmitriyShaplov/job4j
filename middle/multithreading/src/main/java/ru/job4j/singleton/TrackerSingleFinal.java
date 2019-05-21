package ru.job4j.singleton;

import ru.job4j.tracker.Item;

/**
 * Via final field.
 * Non-lazy.
 * @author shaplov
 * @since 21.05.2019
 */
public class TrackerSingleFinal {

    private static final TrackerSingleFinal INSTANCE = new TrackerSingleFinal();

    private TrackerSingleFinal() {
    }

    public static TrackerSingleFinal getInstance() {
        return INSTANCE;
    }

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleFinal tracker = TrackerSingleFinal.getInstance();
    }
}

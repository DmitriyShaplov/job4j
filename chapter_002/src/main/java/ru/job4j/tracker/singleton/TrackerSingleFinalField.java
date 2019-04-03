package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.util.List;

/**
 * Tracker Singleton pattern using static final field.
 * Eager loading.
 * @author shaplov
 * @since 03.04.2019
 */
public class TrackerSingleFinalField {

    private static final TrackerSingleFinalField INSTANCE = new TrackerSingleFinalField();
    private final Tracker tracker = new Tracker();

    private TrackerSingleFinalField() {
    }

    public static TrackerSingleFinalField getInstance() {
        return INSTANCE;
    }

    public Item add(Item item) {
        return this.tracker.add(item);
    }

    public boolean replace(String id, Item item) {
        return this.tracker.replace(id, item);
    }

    public boolean delete(String id) {
        return this.tracker.delete(id);
    }

    public List<Item> findAdd() {
        return this.tracker.findAll();
    }

    public List<Item> findByName(String key) {
        return this.tracker.findByName(key);
    }

    public Item findById(String id) {
        return this.tracker.findById(id);
    }
}

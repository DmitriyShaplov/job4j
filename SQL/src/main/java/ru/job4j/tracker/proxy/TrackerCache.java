package ru.job4j.tracker.proxy;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.util.List;
import java.util.WeakHashMap;

public class TrackerCache implements ITracker {

    private final WeakHashMap<String, Item> cache = new WeakHashMap<>();

    @Override
    public Item add(Item item) {
        this.cache.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(String id) {
        return this.cache.get(id);
    }

    @Override
    public boolean replace(String id, Item item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public List<Item> findByName(String key) {
        return null;
    }
}

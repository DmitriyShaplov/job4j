package ru.job4j.tracker.proxy;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.util.List;

public class TrackerProxy implements ITracker {

    private final ITracker cache;
    private final ITracker sql;

    public TrackerProxy(ITracker cache, ITracker sql) {
        this.cache = cache;
        this.sql = sql;
    }

    @Override
    public Item add(Item item) {
        sql.add(item);
        cache.add(item);
        return item;
    }

    @Override
    public Item findById(String id) {
        Item item = cache.findById(id);
        if (item == null) {
            item = sql.findById(id);
            cache.add(item);
        }
        return item;
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

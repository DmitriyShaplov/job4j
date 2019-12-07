package ru.shaplov.job4j.observer.tracker;

import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaplov
 * @since 07.12.2019
 */
public class TrackerSender implements ITracker, Observe<Messenger<Item>> {

    private final ITracker tracker;
    private final List<Messenger<Item>> subscribers = new ArrayList<>();

    public TrackerSender(ITracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public Item add(Item item) {
        tracker.add(item);
        subscribers.forEach(msg -> msg.send(item));
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        return tracker.replace(id, item);
    }

    @Override
    public boolean delete(String id) {
        return tracker.delete(id);
    }

    @Override
    public List<Item> findAll() {
        return tracker.findAll();
    }

    @Override
    public List<Item> findByName(String key) {
        return tracker.findByName(key);
    }

    @Override
    public Item findById(String id) {
        return tracker.findById(id);
    }

    @Override
    public void subscribe(Messenger<Item> client) {
        subscribers.add(client);
    }

    @Override
    public void unsubscribe(Messenger<Item> client) {
        subscribers.remove(client);
    }
}

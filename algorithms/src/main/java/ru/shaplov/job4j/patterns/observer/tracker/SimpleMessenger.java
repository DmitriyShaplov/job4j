package ru.shaplov.job4j.patterns.observer.tracker;

import ru.job4j.tracker.Item;

/**
 * @author shaplov
 * @since 07.12.2019
 */
public class SimpleMessenger implements Messenger<Item> {
    @Override
    public void send(Item event) {
        System.out.println(event);
    }
}

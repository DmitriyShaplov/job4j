package ru.shaplov.job4j.observer.tracker;

import ru.job4j.tracker.ConsoleInput;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.Tracker;

/**
 * @author shaplov
 * @since 07.12.2019
 */
public class Main {
    public static void main(String[] args) {
        TrackerSender tracker = new TrackerSender(new Tracker());
        Messenger<Item> messenger = new SimpleMessenger();
        tracker.subscribe(messenger);
        new StartUI(new ConsoleInput(), tracker, System.out::println).init();
    }
}

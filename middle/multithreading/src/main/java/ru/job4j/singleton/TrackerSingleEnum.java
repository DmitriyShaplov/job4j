package ru.job4j.singleton;

import ru.job4j.tracker.Item;

/**
 * Non-lazy Enum realization.
 * Enum безопасно публикуется всем клиентам в многопоточной среде.
 */
public enum TrackerSingleEnum {
    INSTANCE;

    public Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingleEnum tracker = TrackerSingleEnum.INSTANCE;
    }
}

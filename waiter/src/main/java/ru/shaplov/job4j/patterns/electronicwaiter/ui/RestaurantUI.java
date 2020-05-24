package ru.shaplov.job4j.patterns.electronicwaiter.ui;

import ru.shaplov.job4j.patterns.electronicwaiter.User;

public interface RestaurantUI {
    void drawMenu();
    void printInitMessage(User user);
}

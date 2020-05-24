package ru.shaplov.job4j.patterns.electronicwaiter.factory.api;

import ru.shaplov.job4j.patterns.electronicwaiter.Order;

import java.util.Map;

public interface OrderFactory {
    Order createOrder(String name);
    Order addAdditivity(Order order, String name);
    Map<String, Double> getAdditives();
    Map<String, Double> getItems();
}

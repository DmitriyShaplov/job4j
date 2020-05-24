package ru.shaplov.job4j.patterns.electronicwaiter.factory.api;

import java.util.Map;

public interface FoodTypeFactory {

    OrderFactory getOrderFactory(String name);
    Map<String, OrderFactory> getOrderFactories();
}

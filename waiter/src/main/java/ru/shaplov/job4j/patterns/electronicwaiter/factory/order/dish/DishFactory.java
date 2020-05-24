package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.dish;

import ru.shaplov.job4j.patterns.electronicwaiter.factory.order.AbstractOrderFactory;

import java.util.Map;

public abstract class DishFactory extends AbstractOrderFactory {
    public DishFactory(Map<String, Double> dish) {
        super(dish);
    }

    @Override
    protected String packageToScan() {
        return "ru.shaplov.job4j.patterns.electronicwaiter.menu.dish.additives";
    }
}

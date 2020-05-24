package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.drink;


import ru.shaplov.job4j.patterns.electronicwaiter.factory.order.AbstractOrderFactory;

import java.util.Map;

public abstract class DrinkFactory extends AbstractOrderFactory {
    public DrinkFactory(Map<String, Double> drinks) {
        super(drinks);
    }

    @Override
    protected String packageToScan() {
        return "ru.shaplov.job4j.patterns.electronicwaiter.menu.drink.additives";
    }
}

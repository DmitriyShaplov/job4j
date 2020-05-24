package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.desert;

import ru.shaplov.job4j.patterns.electronicwaiter.factory.order.AbstractOrderFactory;

import java.util.Map;

public abstract class DessertFactory extends AbstractOrderFactory {
    public DessertFactory(Map<String, Double> deserts) {
        super(deserts);
    }

    @Override
    protected String packageToScan() {
        return "ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert.additives";
    }
}

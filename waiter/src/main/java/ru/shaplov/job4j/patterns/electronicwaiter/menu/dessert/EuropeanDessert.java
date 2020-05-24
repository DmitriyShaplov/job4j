package ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class EuropeanDessert extends AbstractOrder implements Dessert {
    public EuropeanDessert(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Европейский десерт. ";
    }
}


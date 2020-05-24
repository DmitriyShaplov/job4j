package ru.shaplov.job4j.patterns.electronicwaiter.menu.drink;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class EuropeanDrink extends AbstractOrder implements Drink {

    public EuropeanDrink(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Европейский напиток. ";
    }
}

package ru.shaplov.job4j.patterns.electronicwaiter.menu.dish;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class EuropeanDish extends AbstractOrder implements Dish {

    public EuropeanDish(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Евпроейское основное блюдо. ";
    }
}

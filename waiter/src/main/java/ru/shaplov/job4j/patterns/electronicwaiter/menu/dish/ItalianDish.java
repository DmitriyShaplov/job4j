package ru.shaplov.job4j.patterns.electronicwaiter.menu.dish;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class ItalianDish extends AbstractOrder implements Dish {

    public ItalianDish(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Итальянское основное блюдо. ";
    }
}
package ru.shaplov.job4j.patterns.electronicwaiter.menu.drink;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class ItalianDrink extends AbstractOrder implements Drink {

    public ItalianDrink(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Итальянский напиток. ";
    }
}

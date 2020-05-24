package ru.shaplov.job4j.patterns.electronicwaiter.menu.drink;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class JapaneseDrink extends AbstractOrder implements Drink {

    public JapaneseDrink(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Японский напиток. ";
    }
}

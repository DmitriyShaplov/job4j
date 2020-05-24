package ru.shaplov.job4j.patterns.electronicwaiter.menu.dish;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class JapaneseDish extends AbstractOrder implements Dish {

    public JapaneseDish(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Японское основное блюдо. ";
    }
}

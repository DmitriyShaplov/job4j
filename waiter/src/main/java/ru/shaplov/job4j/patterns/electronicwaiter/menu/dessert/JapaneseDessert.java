package ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert;

import ru.shaplov.job4j.patterns.electronicwaiter.AbstractOrder;

public class JapaneseDessert extends AbstractOrder implements Dessert {
    public JapaneseDessert(String name, double cost) {
        super(name, cost);
    }

    @Override
    protected String getPrefix() {
        return "Японский десерт. ";
    }
}

package ru.shaplov.job4j.patterns.electronicwaiter.menu.dish.additives;

import ru.shaplov.job4j.patterns.electronicwaiter.Order;
import ru.shaplov.job4j.patterns.electronicwaiter.annotation.Additivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.AbstractAdditivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.dish.Dish;

@Additivity(value = "Майонез", price = 5.0)
public class Mayonnaise extends AbstractAdditivity implements Dish {
    public Mayonnaise(Order order) {
        super(order);
        if (!Dish.class.isAssignableFrom(order.getClass())) {
            throw new IllegalArgumentException("не основное блюдо.");
        }
    }
}

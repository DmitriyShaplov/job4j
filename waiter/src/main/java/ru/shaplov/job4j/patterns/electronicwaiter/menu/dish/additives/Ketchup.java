package ru.shaplov.job4j.patterns.electronicwaiter.menu.dish.additives;

import ru.shaplov.job4j.patterns.electronicwaiter.Order;
import ru.shaplov.job4j.patterns.electronicwaiter.annotation.Additivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.AbstractAdditivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.dish.Dish;

@Additivity(value = "Кетчуп", price = 20.0)
public class Ketchup extends AbstractAdditivity implements Dish {
    public Ketchup(Order order) {
        super(order);
        if (!Dish.class.isAssignableFrom(order.getClass())) {
            throw new IllegalArgumentException("не основное блюдо.");
        }
    }
}

package ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert.additives;

import ru.shaplov.job4j.patterns.electronicwaiter.Order;
import ru.shaplov.job4j.patterns.electronicwaiter.annotation.Additivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.AbstractAdditivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert.Dessert;

@Additivity(value = "Сироп", price = 30.0)
public class Syrup extends AbstractAdditivity implements Dessert {
    public Syrup(Order order) {
        super(order);
        if (!Dessert.class.isAssignableFrom(order.getClass())) {
            throw new IllegalArgumentException("не десерт.");
        }
    }
}

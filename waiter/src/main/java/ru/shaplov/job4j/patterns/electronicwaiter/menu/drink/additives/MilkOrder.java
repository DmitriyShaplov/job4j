package ru.shaplov.job4j.patterns.electronicwaiter.menu.drink.additives;

import ru.shaplov.job4j.patterns.electronicwaiter.Order;
import ru.shaplov.job4j.patterns.electronicwaiter.annotation.Additivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.AbstractAdditivity;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.drink.Drink;

@Additivity(value = "Молоко", price = 20.0)
public class MilkOrder extends AbstractAdditivity implements Drink {
    public MilkOrder(Order order) {
        super(order);
        if (!Drink.class.isAssignableFrom(order.getClass())) {
            throw new IllegalArgumentException("не напиток.");
        }
    }
}

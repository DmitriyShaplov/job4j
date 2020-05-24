package ru.shaplov.job4j.patterns.electronicwaiter.menu;

import ru.shaplov.job4j.patterns.electronicwaiter.Order;
import ru.shaplov.job4j.patterns.electronicwaiter.User;
import ru.shaplov.job4j.patterns.electronicwaiter.annotation.Additivity;

public abstract class AbstractAdditivity implements Order {

    private final Order order;

    public AbstractAdditivity(Order order) {
        this.order = order;
    }

    @Override
    public boolean isReady() {
        return order.isReady();
    }

    @Override
    public void setReady() {
        order.setReady();
    }

    @Override
    public User getUser() {
        return order.getUser();
    }

    @Override
    public String getDescription() {
        Additivity annotation = this.getClass().getAnnotation(Additivity.class);
        String additivity = annotation.value();
        if (order.getClass().isAnnotationPresent(Additivity.class)) {
            return order.getDescription() + " +" + additivity + ".";
        } else {
            return order.getDescription() + " Добавки: +" + additivity + ".";
        }
    }

    @Override
    public Double getCost() {
        Additivity annotation = this.getClass().getAnnotation(Additivity.class);
        double price = annotation.price();
        return order.getCost() + price;
    }

    @Override
    public void setUser(User user) {
        order.setUser(user);
    }
}

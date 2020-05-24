package ru.shaplov.job4j.patterns.electronicwaiter;

import java.util.Objects;

/**
 * Класс клиента.
 *
 * @author shaplov
 * @since 22.02.2020
 */
public class User implements OrderSubscriber {

    /**
     * Идентификатор пользователя. Любая строка
     */
    private final String id;
    private Order order;

    public User(String id) {
        this.id = id;
    }

    public void orderAccepted() {
        System.out.println("Заказ принят, цена: " + order.getCost() + " руб.");
    }

    public void orderReady() {
        System.out.println("Order ready! " + order.getDescription());
    }

    public String getId() {
        return id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(order, user.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order);
    }

    @Override
    public void reactOnOrderIsReady(Order order) {
        if (order == this.order) {
            orderReady();
        } else {
            throw new IllegalStateException("Не мой заказ");
        }
    }

    @Override
    public void reactOnOrderAccepted(Order order) {
        if (order == this.order) {
            orderAccepted();
        } else {
            throw new IllegalStateException("Не мой заказ");
        }
    }
}

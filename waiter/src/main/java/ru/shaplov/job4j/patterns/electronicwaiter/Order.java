package ru.shaplov.job4j.patterns.electronicwaiter;

/**
 * Интерфейс сущности заказа.
 *
 * @author shaplov
 * @since 22.02.2020
 */
public interface Order {
    boolean isReady();
    void setReady();
    User getUser();
    String getDescription();
    Double getCost();
    void setUser(User user);
}

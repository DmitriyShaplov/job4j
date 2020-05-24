package ru.shaplov.job4j.patterns.electronicwaiter.factory;

import lombok.Getter;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.FoodTypeFactory;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.OrderFactory;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractFoodFactory implements FoodTypeFactory {

    @Getter
    private Map<String, OrderFactory> orderFactories;

    public AbstractFoodFactory(Map<String, OrderFactory> factories) {
        this.orderFactories = factories.entrySet().stream()
                .filter(entry -> entry.getValue().getClass().getSimpleName().startsWith(getType()))
                .collect(Collectors.toMap(entry -> entry.getKey().split(":")[0],
                        Map.Entry::getValue));
    }

    @Override
    public OrderFactory getOrderFactory(String name) {
        return Optional.ofNullable(orderFactories.get(name))
                .orElseThrow(() -> new NoSuchElementException("Нет такого типа еды"));
    }

    protected abstract String getType();
}

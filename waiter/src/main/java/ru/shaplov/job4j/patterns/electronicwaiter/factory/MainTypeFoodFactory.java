package ru.shaplov.job4j.patterns.electronicwaiter.factory;

import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.FoodTypeFactory;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MainTypeFoodFactory {

    @Getter
    private final Map<String, FoodTypeFactory> factories;

    public MainTypeFoodFactory(Map<String, FoodTypeFactory> factories) {
        this.factories = factories;
    }

    public FoodTypeFactory getFactoryByName(String name) {
        return Optional.ofNullable(factories.get(name)).orElseThrow(
                () -> new NoSuchElementException("Не бывает такого типа кухни: " + name));
    }
}

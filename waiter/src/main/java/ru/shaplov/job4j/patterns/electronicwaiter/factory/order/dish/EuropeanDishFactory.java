package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.dish;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.dish.EuropeanDish;

import java.util.Map;

@Service("Основное:европейский")
@PropertySource(value = "classpath:menu.properties", encoding = "UTF-8")
public class EuropeanDishFactory extends DishFactory {

    public EuropeanDishFactory(@Value("#{${european.dish}}") Map<String, Double> dish) {
        super(dish);
    }

    @Override
    protected Class orderType() {
        return EuropeanDish.class;
    }
}
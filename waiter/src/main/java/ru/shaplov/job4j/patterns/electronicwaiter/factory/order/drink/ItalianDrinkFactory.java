package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.drink;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.drink.ItalianDrink;

import java.util.Map;

@Service("Напиток:итальянский")
@PropertySource(value = "classpath:menu.properties", encoding = "UTF-8")
public class ItalianDrinkFactory extends DrinkFactory {

    public ItalianDrinkFactory(@Value("#{${italian.drink}}") Map<String, Double> drinks) {
        super(drinks);
    }

    @Override
    protected Class orderType() {
        return ItalianDrink.class;
    }
}

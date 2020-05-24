package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.desert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert.ItalianDessert;

import java.util.Map;

@Service("Десерт:итальянский")
@PropertySource(value = "classpath:menu.properties", encoding = "UTF-8")
public class ItalianDessertFactory extends DessertFactory {

    public ItalianDessertFactory(@Value("#{${italian.dessert}}") Map<String, Double> dessert) {
        super(dessert);
    }

    @Override
    protected Class orderType() {
        return ItalianDessert.class;
    }
}
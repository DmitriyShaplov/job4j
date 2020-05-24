package ru.shaplov.job4j.patterns.electronicwaiter.factory.order.desert;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.menu.dessert.EuropeanDessert;

import java.util.Map;

@Service("Десерт:европейский")
@PropertySource(value = "classpath:menu.properties", encoding = "UTF-8")
public class EuropeanDessertFactory extends DessertFactory {

    public EuropeanDessertFactory(@Value("#{${european.dessert}}") Map<String, Double> dessert) {
        super(dessert);
    }

    @Override
    protected Class orderType() {
        return EuropeanDessert.class;
    }
}
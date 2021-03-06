package ru.shaplov.job4j.patterns.electronicwaiter.factory;

import org.springframework.stereotype.Service;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.OrderFactory;

import java.util.Map;

@Service("Итальянская")
public class ItalianFactory extends AbstractFoodFactory {
    public ItalianFactory(Map<String, OrderFactory> factories) {
        super(factories);
    }

    @Override
    protected String getType() {
        return "Italian";
    }
}

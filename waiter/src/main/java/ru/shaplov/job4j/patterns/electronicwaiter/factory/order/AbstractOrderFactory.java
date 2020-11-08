package ru.shaplov.job4j.patterns.electronicwaiter.factory.order;

import lombok.Getter;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import ru.shaplov.job4j.patterns.electronicwaiter.Order;
import ru.shaplov.job4j.patterns.electronicwaiter.annotation.Additivity;
import ru.shaplov.job4j.patterns.electronicwaiter.factory.api.OrderFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public abstract class AbstractOrderFactory implements OrderFactory {

    private final Map<String, Function<Order, Order>> additivityFunctions = new HashMap<>();

    @Getter
    private final Map<String, Double> additives = new HashMap<>();
    @Getter
    private final Map<String, Double> items;

    protected AbstractOrderFactory(Map<String, Double> items) {
        this.items = items;
    }

    /**
     * просканировать определенный пакет с классами-декораторами.
     * получить список классов.
     * добавить их в additives.
     */
    @PostConstruct
    @SneakyThrows
    public void initAdditives() {
        Reflections scanner = new Reflections(packageToScan());
        Set<Class<? extends Order>> additivesClasses = scanner.getSubTypesOf(Order.class);
        additivesClasses.forEach(
                clazz -> {
                    if (clazz.isAnnotationPresent(Additivity.class)) {
                        Additivity annotation = clazz.getAnnotation(Additivity.class);
                        additives.put(annotation.value(), annotation.price());
                        additivityFunctions.put(annotation.value(), order -> {
                            try {
                                return clazz.getConstructor(Order.class).newInstance(order);
                            } catch (Exception e) {
                                throw new RuntimeException();
                            }
                        });
                    }
                }
        );
    }

    protected abstract String packageToScan();
    protected abstract Class orderType();

    @Override
    @SneakyThrows
    public Order createOrder(String name) {
        if (items.containsKey(name)) {
            return (Order) orderType().getConstructor(String.class, Double.TYPE).newInstance(name, items.get(name));
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Order addAdditivity(Order order, String name) {
        return this.additivityFunctions.get(name).apply(order);
    }
}

package ru.shaplov.job4j.patterns.builder;

import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Примитивный аналог Stream для числовых значений.
 */
public class Flow {
    private final List<Integer> values;

    private Flow(List<Integer> values) {
        this.values = values;
    }

    /**
     * Создает новый Flow из входных элементов.
     *
     * @param values список элементов
     * @return объект Flow
     */
    public static Flow of(Integer... values) {
        return new Flow(new ArrayList<>(Arrays.asList(values)));
    }

    /**
     * Фильтрует наш Флоу по предикату.
     *
     * @param predicate фильтр
     * @return Flow
     */
    public Flow filter(Predicate<Integer> predicate) {
        Objects.requireNonNull(predicate);
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (!predicate.test(next)) {
                iterator.remove();
            }
        }
        return this;
    }

    /**
     * Собирает объекты в переданную коллекцию.
     *
     * @param supplier поставщик коллекции
     * @return коллекция с объектами
     */
    public Collection<Integer> collect(Supplier<Collection<Integer>> supplier) {
        Collection<Integer> collection = supplier.get();
        collection.addAll(values);
        return collection;
    }

    /**
     * Применяет функцию сокращения на элементы коллекции и возвращает результат.
     *
     * @param binaryOperator функция редуктора
     * @return результат вычислений
     */
    public Integer reduce(IntBinaryOperator binaryOperator) {
        Iterator<Integer> iterator = values.iterator();
        if (!iterator.hasNext()) {
            return 0;
        }
        Integer left = iterator.next();
        while (iterator.hasNext()) {
            left = binaryOperator.applyAsInt(left, iterator.next());
        }
        return left;
    }
}

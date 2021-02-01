package ru.shaplov.job4j.patterns.builder.pipeline;

import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

/**
 * Примитивный аналог Stream для числовых значений.
 */
public class Flow {

    private final Collection<Integer> values;

    private Step currentStep;

    private Flow(Collection<Integer> values, Step step) {
        this.currentStep = step;
        this.values = values;
    }

    /**
     * Создает новый Flow из входных элементов.
     *
     * @param values список элементов
     * @return объект Flow
     */
    public static Flow of(Integer... values) {
        return new Flow(new ArrayList<>(Arrays.asList(values)), input -> input);
    }

    /**
     * Фильтрует наш Флоу по предикату.
     *
     * @param predicate фильтр
     * @return Flow
     */
    public Flow filter(IntPredicate predicate) {
        Step nextStep = input -> {
            Objects.requireNonNull(predicate);
            input.removeIf(next -> !predicate.test(next));
            return input;
        };
        Step lastStep = currentStep;
        currentStep = iter -> nextStep.process(lastStep.process(iter));
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
        Collection<Integer> terminatedValues = currentStep.process(this.values);
        collection.addAll(terminatedValues);
        return collection;
    }

    /**
     * Применяет функцию сокращения на элементы коллекции и возвращает результат.
     *
     * @param binaryOperator функция редуктора
     * @return результат вычислений
     */
    public Integer reduce(IntBinaryOperator binaryOperator) {
        Collection<Integer> terminatedValues = currentStep.process(this.values);
        Iterator<Integer> terminatedIterator = terminatedValues.iterator();
        if (!terminatedIterator.hasNext()) {
            return 0;
        }
        Integer left = terminatedIterator.next();
        while (terminatedIterator.hasNext()) {
            left = binaryOperator.applyAsInt(left, terminatedIterator.next());
        }
        return left;
    }

    /**
     * Шаг процесса.
     */
    @FunctionalInterface
    private static interface Step {
        Collection<Integer> process(Collection<Integer> input);
    }
}

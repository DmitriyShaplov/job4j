package ru.shaplov.job4j.patterns.builder.cor;

import java.util.*;
import java.util.function.IntBinaryOperator;
import java.util.function.IntPredicate;
import java.util.function.Supplier;

/**
 * Примитивный аналог Stream для числовых значений.
 */
public class Flow {

    private final Collection<Integer> values;

    private Step startStep;
    private Step currentStep;

    private Flow(Collection<Integer> values) {
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
    public Flow filter(IntPredicate predicate) {
        Step nextStep = new Step() {
            @Override
            Collection<Integer> process(Collection<Integer> input) {
                Objects.requireNonNull(predicate);
                input.removeIf(next -> !predicate.test(next));
                return input;
            }
        };
        if (currentStep != null) {
            currentStep.link(nextStep);
        } else {
            startStep = nextStep;
        }
        this.currentStep = nextStep;
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
        Collection<Integer> terminatedValues = startStep.execute(this.values);
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
        Collection<Integer> terminatedValues = startStep.execute(this.values);
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
    private abstract static class Step {
        private Step nextStep;

        public void link(Step nextStep) {
            this.nextStep = nextStep;
        }

        public Collection<Integer> execute(Collection<Integer> input) {
            Collection<Integer> process = process(input);
            if (nextStep != null) {
                return nextStep.process(process);
            }
            return process;
        }

        abstract Collection<Integer> process(Collection<Integer> input);
    }
}

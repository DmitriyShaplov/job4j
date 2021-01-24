package ru.shaplov.job4j.patterns.proxy;

/**
 * Интерфейс технической поддержки с возможностью обращения.
 */
public interface TechnicalSupport {

    /**
     * Совершить звонок в техническую поддержку.
     *
     * @param problemLevel уровень проблемы клиента
     * @return результат обращения
     */
    boolean call(ProblemLevel problemLevel);
}

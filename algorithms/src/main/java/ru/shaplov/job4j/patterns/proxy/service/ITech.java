package ru.shaplov.job4j.patterns.proxy.service;

import ru.shaplov.job4j.patterns.proxy.ProblemLevel;

/**
 * Технический интерфейс технической поддержки, для определения ее уровня.
 */
public interface ITech {

    /**
     * Возвращает решаемый уровень проблемы.
     *
     * @return решаемый уровень проблемы
     */
    ProblemLevel level();
}

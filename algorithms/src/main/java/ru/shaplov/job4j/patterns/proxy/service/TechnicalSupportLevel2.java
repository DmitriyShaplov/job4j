package ru.shaplov.job4j.patterns.proxy.service;

import ru.shaplov.job4j.patterns.proxy.ProblemLevel;

/**
 * Вторая линия техподдержки.
 */
public class TechnicalSupportLevel2 extends AbstractRandomTechnicalSupport {

    @Override
    public ProblemLevel level() {
        return ProblemLevel.LEVEL_2;
    }
}

package ru.shaplov.job4j.patterns.proxy.service;

import ru.shaplov.job4j.patterns.proxy.ProblemLevel;

/**
 * Первая линия техподдержки.
 */
public class TechnicalSupportLevel1 extends AbstractRandomTechnicalSupport implements ITech {

    @Override
    public ProblemLevel level() {
        return ProblemLevel.LEVEL_1;
    }
}

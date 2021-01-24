package ru.shaplov.job4j.patterns.proxy.service;

import ru.shaplov.job4j.patterns.proxy.ProblemLevel;
import ru.shaplov.job4j.patterns.proxy.TechnicalSupport;

/**
 * Третья линия техподдержки. Решает любые проблемы.
 */
public class TechnicalSupportLevel3 implements TechnicalSupport, ITech {
    @Override
    public boolean call(ProblemLevel problemLevel) {
        return true;
    }

    @Override
    public ProblemLevel level() {
        return ProblemLevel.LEVEL_3;
    }
}

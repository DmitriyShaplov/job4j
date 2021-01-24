package ru.shaplov.job4j.patterns.proxy.service;

import ru.shaplov.job4j.patterns.proxy.ProblemLevel;
import ru.shaplov.job4j.patterns.proxy.TechnicalSupport;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Абстрактный класс для всех линий технической поддержки.
 */
public abstract class AbstractRandomTechnicalSupport implements TechnicalSupport, ITech {

    private final Random random = ThreadLocalRandom.current();

    @Override
    public boolean call(ProblemLevel problemLevel) {
        return problemLevel == level() || tryToSolveProblem();
    }

    protected boolean tryToSolveProblem() {
        return random.nextBoolean();
    }
}

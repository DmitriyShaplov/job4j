package ru.shaplov.job4j.patterns.proxy;

import java.util.EnumSet;

/**
 * Уровни технической проблемы.
 */
public enum ProblemLevel {
    UNKNOWN("unknown"),
    LEVEL_1("1"),
    LEVEL_2("2"),
    LEVEL_3("3");

    private final String level;

    ProblemLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    /**
     * Фабрика для получения инстанса ProblemLevel по входной строке.
     *
     * @param str строка с уровнем
     * @return инстанс класса
     */
    public static ProblemLevel of(String str) {
        return EnumSet.allOf(ProblemLevel.class).stream()
                .filter(e -> e.level.equals(str))
                .findFirst().orElse(UNKNOWN);
    }
}

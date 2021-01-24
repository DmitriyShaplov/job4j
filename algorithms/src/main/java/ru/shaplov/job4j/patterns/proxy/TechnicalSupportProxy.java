package ru.shaplov.job4j.patterns.proxy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.shaplov.job4j.patterns.proxy.service.ITech;
import ru.shaplov.job4j.patterns.proxy.service.TechnicalSupportLevel1;
import ru.shaplov.job4j.patterns.proxy.service.TechnicalSupportLevel2;
import ru.shaplov.job4j.patterns.proxy.service.TechnicalSupportLevel3;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Главный обработчик клиентских звонков.
 */
public class TechnicalSupportProxy implements TechnicalSupport {

    private final Logger log = LogManager.getLogger(TechnicalSupportProxy.class);

    private final Map<ProblemLevel, TechnicalSupport> supportMap = new LinkedHashMap<>();

    public TechnicalSupportProxy() {
        supportMap.put(ProblemLevel.LEVEL_1, new TechnicalSupportLevel1());
        supportMap.put(ProblemLevel.LEVEL_2, new TechnicalSupportLevel2());
        supportMap.put(ProblemLevel.LEVEL_3, new TechnicalSupportLevel3());
    }

    @Override
    public boolean call(ProblemLevel problemLevel) {
        //если проблема известна, делегируем выполнение нужной линии
        boolean result = false;
        if (supportMap.containsKey(problemLevel)) {
            log.debug("Проблема известна! Решает техподдержка уровня {}", problemLevel.getLevel());
            result = supportMap.get(problemLevel).call(problemLevel);
            log.debug("Результат решения: {}", result);
            return result;
        }
        //иначе пытаемся пройтись по всем линиям
        log.debug("Проблема неизвестна, пытаемся решить...");
        Iterator<TechnicalSupport> iterator = supportMap.values().iterator();
        while (!result && iterator.hasNext()) {
            TechnicalSupport tech = iterator.next();
            result = tech.call(problemLevel);
            log.debug("Проблему решал оператор уровня={}, результат={}", ((ITech) tech).level(), result);
        }
        return result;
    }
}

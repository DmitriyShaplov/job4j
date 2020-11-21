package ru.shaplov.job4j.patterns.singleton;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для хранения запасов воды.
 * Singleton, eager loading.
 */
public enum Reservoir {

    INSTANCE;

    /**
     * Максимальная емкость резервуара.
     */
    private static final long MAX_VALUE = 1000;

    private static final Logger LOG = LogManager.getLogger(Reservoir.class);

    @Getter
    private final AtomicLong waterValue = new AtomicLong();

    /**
     * Метод пополнения воды.
     * @param value количество воды.
     */
    public void replenishWater(long value) {
        long curVal = waterValue.accumulateAndGet(value, (left, right) -> Math.min(left + right, MAX_VALUE));
        LOG.debug("Пополнение на: {}, текущий объем: {}", value, curVal);
    }

    /**
     * Потребление воды.
     * @param value сколько воды нужно забрать
     * @return реальная отдача
     */
    public long consumeWater(long value) {
        AtomicLong toReturn = new AtomicLong();
        long curVal = waterValue.accumulateAndGet(value, (left, right) -> {
            long res = Math.max(left - right, 0);
            toReturn.set(left - res);
            return res;
        });
        long actuallyReturned = toReturn.get();
        LOG.debug("Запрашиваемый объем: {}, был отдан: {}, текущий объем: {}", value, actuallyReturned, value);
        return actuallyReturned;
    }
}

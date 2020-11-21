package ru.shaplov.job4j.patterns.singleton;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Реализация потребителя воды.
 */
public class HouseImpl implements House {

    private static final Logger LOG = LogManager.getLogger(HouseImpl.class);

    private final Reservoir reservoir = Reservoir.INSTANCE;

    /**
     * Потребность в воде в единицу времени.
     */
    private final long needs;

    private final AtomicLong consumedWaterValue = new AtomicLong();

    public HouseImpl(long needs) {
        this.needs = needs;
    }

    @Override
    public void startConsuming() {
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            long gainedWater = reservoir.consumeWater(needs);
            long curVal = consumedWaterValue.updateAndGet(operand -> operand + gainedWater);
            LOG.debug("Дом с потребностью {} текущее значение потребленной воды: {}", needs, curVal);
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}

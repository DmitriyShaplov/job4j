package ru.shaplov.job4j.patterns.singleton;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Класс для пополнения запасов воды. Источник.
 */
public class Source {

    private static final Logger LOG = LogManager.getLogger(Source.class);

    private final long sourcePower;

    public Source(long sourcePower) {
        this.sourcePower = sourcePower;
    }

    /**
     * Начать поставки воды.
     */
    public void startSupply() {
        Reservoir reservoir = Reservoir.INSTANCE;
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
                    reservoir.replenishWater(sourcePower);
                    LOG.debug("Поставлено {} воды.", sourcePower);
                },
                0, 2, TimeUnit.SECONDS);
    }
}

package ru.shaplov.job4j.patterns.singleton;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class WaterSystemMain {

    public static void main(String[] args) {
        configureLoggers();

        new Source(150).startSupply();
        new HouseImpl(15).startConsuming();
        new HouseImpl(25).startConsuming();
        new HouseImpl(35).startConsuming();
    }

    private static void configureLoggers() {
        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(Level.DEBUG);
        ctx.updateLoggers();
    }
}

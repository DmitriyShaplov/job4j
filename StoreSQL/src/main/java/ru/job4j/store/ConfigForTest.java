package ru.job4j.store;

import java.io.File;
import java.util.Properties;

public class ConfigForTest extends Config {
    private final Properties values = new Properties();

    @Override
    public void init() {
        this.values.setProperty("url", "jdbc:sqlite:"
                + System.getProperty("java.io.tmpdir") + File.separator + "db" + File.separator + "test.db");
    }

    @Override
    public String get(String key) {
        return this.values.getProperty(key);
    }
}

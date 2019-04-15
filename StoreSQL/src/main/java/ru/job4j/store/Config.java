package ru.job4j.store;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class for load property for connection to db.
 * @author shaplov
 * @since 08.04.2019
 */
public class Config {

    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }
}

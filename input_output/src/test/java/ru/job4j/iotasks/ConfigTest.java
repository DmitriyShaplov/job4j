package ru.job4j.iotasks;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 01.04.2019
 */
public class ConfigTest {

    @Test
    public void whenPutValueThenGetThisValue() throws IOException {
        Config config = new Config("test.properties");
        config.put("testKey", "testValue");
        assertThat(config.get("testKey"), is("testValue"));
        Files.deleteIfExists(Paths.get("test.properties"));
    }

    @Test
    public void whenGetKeyDoesntExistThenNull() throws IOException {
        Config config = new Config("test.properties");
        assertNull(config.get("test"));
        Files.deleteIfExists(Paths.get("test.properties"));
    }

    @Test
    public void whenPutTwoValuesThenReplaceFirstAndGetItResultNewValue() throws IOException {
        Config config = new Config("test.properties");
        config.put("testKey", "testValue");
        config.put("testKey1", "testValue1");
        config.put("testKey", "newValue");
        assertThat(config.get("testKey"), is("newValue"));
        Files.deleteIfExists(Paths.get("test.properties"));
    }

    @Test
    public void whenPutValueInConfigFileItDoesntDeletes() throws IOException {
        Config first = new Config("test");
        first.put("Key", "Value");
        Config second = new Config("test");
        assertThat(second.get("Key"), is("Value"));
        Files.deleteIfExists(Paths.get("test"));
    }
}
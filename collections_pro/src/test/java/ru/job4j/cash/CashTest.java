package ru.job4j.cash;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 01.05.2019
 */
public class CashTest {

    @Test
    public void whenGetValueByKeyThenReturnValue() throws Exception {
        Cash cash = new StoredCash();
        String result = cash.getCash("Names.txt");
        String expected = String.join(System.lineSeparator(),
                "David",
                "John",
                "Parker",
                "Tony") + System.lineSeparator();
        assertThat(result, is(expected));
    }
}
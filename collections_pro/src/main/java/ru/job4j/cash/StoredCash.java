package ru.job4j.cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Cash realization via load values from disk.
 * @author shaplov
 * @since 01.05.2019
 */
public class StoredCash extends BaseCash {

    @Override
    protected String loadCash(String key) throws IOException {
        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(StoredCash.class.getClassLoader().getResourceAsStream(key),
                        StandardCharsets.UTF_8))
        ) {
            String scanned;
            StringBuilder sb = new StringBuilder();
            while ((scanned = input.readLine()) != null) {
                sb.append(scanned);
                sb.append(System.lineSeparator());
            }
            String result = sb.toString();
            return result;
        }
    }
}

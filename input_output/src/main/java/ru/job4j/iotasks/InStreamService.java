package ru.job4j.iotasks;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author shaplov
 * @since 18.03.2019
 * @version 1
 */
public class InStreamService {

    public boolean isNumber(InputStream in) throws IOException {
        try (in) {
            return in.read() % 2 == 0;
        }
    }
}

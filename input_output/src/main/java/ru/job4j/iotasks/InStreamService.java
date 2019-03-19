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
        try (InputStream inStream = in) {
            boolean numbers = true;
            int lastByte = -1;
            int c;
            while ((c = inStream.read()) != -1) {
                if (c < 48 || c > 57) {
                    numbers = false;
                    break;
                }
                lastByte = c;
            }
            return lastByte % 2 == 0 && numbers;
        }
    }
}

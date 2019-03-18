package ru.job4j.iotasks;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 18.03.2019
 */
public class InStreamServiceTest {

    @Test
    public void whenInputStreamHasEvenNumberResultTrue() throws IOException {
        byte[] array = new byte[] {2};
        ByteArrayInputStream byteStream = new ByteArrayInputStream(array);
        InStreamService inSer = new InStreamService();
        boolean result = inSer.isNumber(byteStream);
        assertTrue(result);
    }

    @Test
    public void whenInputStreamHasOddNumberResultFalse() throws IOException {
        byte[] array = new byte[] {1};
        ByteArrayInputStream byteStream = new ByteArrayInputStream(array);
        InStreamService inSer = new InStreamService();
        boolean result = inSer.isNumber(byteStream);
        assertFalse(result);
    }
}
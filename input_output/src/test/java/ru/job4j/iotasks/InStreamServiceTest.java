package ru.job4j.iotasks;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 18.03.2019
 */
public class InStreamServiceTest {

    @Test
    public void whenInputStreamHasEvenNumberThenTrue() throws IOException {
        InStreamService service = new InStreamService();
        final boolean number = service.isNumber(new ByteArrayInputStream("1234567891234567890".getBytes()));
        assertTrue(number);
    }

    @Test
    public void whenInputStreamHasOddNumberThenFalse() throws IOException {
        InStreamService service = new InStreamService();
        final boolean number = service.isNumber(new ByteArrayInputStream("423456789123456789".getBytes()));
        assertFalse(number);
    }

    @Test
    public void whenInputStreamHasNotNumberThenFalse() throws IOException {
        InStreamService service = new InStreamService();
        final boolean number = service.isNumber(new ByteArrayInputStream("123мамамылараму7890".getBytes()));
        assertFalse(number);
    }

}
package ru.job4j.iotasks;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.*;

import static org.hamcrest.Matchers.is;
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

    @Test
    public void whenDropAbuseWordsFromStreamThenStreamWithoutIt() throws IOException {
        InStreamService service = new InStreamService();
        String[] abuse = {"нельзя", "прекратить", "завтрашний"};
        OutputStream out = new ByteArrayOutputStream();
        service.dropAbuses(new ByteArrayInputStream(("зачем говорить когда нельзя прекратить влеченный в завтрашний день"
                        + System.lineSeparator()
                        + "прекратить нельзя зачем").getBytes()),
                out, abuse);
        String result = out.toString();
        String expect = "зачем говорить когда влеченный в день" + System.lineSeparator()
                + "зачем";
        assertThat(result, is(expect));
    }
}
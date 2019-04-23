package ru.job4j.solid;

import org.junit.Test;
import ru.job4j.calculator.EngCalculator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author shaplov
 * @since 16.04.2019
 */
public class EngineerCalcTest {

    @Test
    public void whenUseNewOperations() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        ByteArrayInputStream in = new ByteArrayInputStream("90 sin + 4.0 ^ 2 sqrt q".getBytes());
        EngineerCalc interactCalc = new EngineerCalc(new EngCalculator(), ps, in);
        interactCalc.init();
        String expect = String.join(System.lineSeparator(),
                "1.0",
                "5.0",
                "25.0",
                "5.0"
        ) + System.lineSeparator();
        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenUseNewOperations2() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        ByteArrayInputStream in = new ByteArrayInputStream("0 cos c 45 tan q".getBytes());
        EngineerCalc interactCalc = new EngineerCalc(new EngCalculator(), ps, in);
        interactCalc.init();
        String expect = String.join(System.lineSeparator(),
                "1.0",
                "Result reset",
                "1.0"
        ) + System.lineSeparator();
        assertThat(out.toString(), is(expect));
    }
}
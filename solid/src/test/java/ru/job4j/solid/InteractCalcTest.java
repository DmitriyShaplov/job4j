package ru.job4j.solid;

import org.junit.Test;
import ru.job4j.calculator.Calculator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author shaplov
 * @since 16.04.2019
 */
public class InteractCalcTest {

    private static final String LS = System.lineSeparator();

    @Test
    public void whenClearResultEachTime() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        ByteArrayInputStream in = new ByteArrayInputStream("10 * 10 c 12 / 2 q".getBytes());
        InteractCalc interactCalc = new InteractCalc(new Calculator(), ps, in);
        interactCalc.init();
        StringBuilder expect = new StringBuilder();
        expect.append(100.0);
        expect.append(LS);
        expect.append("Result reset");
        expect.append(LS);
        expect.append(6.0);
        expect.append(LS);
        assertThat(out.toString(), is(expect.toString()));
    }

    @Test
    public void whenAppendToResult() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(out);
        ByteArrayInputStream in = new ByteArrayInputStream("10 * 10 / 5 + 4.0 q".getBytes());
        InteractCalc interactCalc = new InteractCalc(new Calculator(), ps, in);
        interactCalc.init();
        StringBuilder expect = new StringBuilder();
        expect.append(100.0);
        expect.append(LS);
        expect.append(20.0);
        expect.append(LS);
        expect.append(24.0);
        expect.append(LS);
        assertThat(out.toString(), is(expect.toString()));
    }
}
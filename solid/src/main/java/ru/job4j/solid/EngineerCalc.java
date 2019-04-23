package ru.job4j.solid;

import ru.job4j.calculator.EngCalculator;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Engineer calculator with additional options.
 * @author shaplov
 * @since 16.04.2019
 */
public class EngineerCalc extends InteractCalc {

    /**
     * Duplicate reference contained in super.
     */
    private final EngCalculator calculator;

    public EngineerCalc(EngCalculator calc, PrintStream output, InputStream in) {
        super(calc, output, in);
        this.calculator = calc;
    }

    @Override
    public void init() {
        addAction("SIN", calculator::sine);
        addAction("COS", calculator::cosine);
        addAction("TAN", calculator::tangent);
        addAction("^", calculator::power);
        addAction("SQRT", calculator::sqrt);
        super.init();
    }

    @Override
    protected String buildMenu() {
        return String.join(System.lineSeparator(),
                "-----------------------------",
                "This is interactive calculator.",
                "Enter the number and chose",
                "one of the following actions",
                "by pressing the specified keys:",
                "'+' add, '-' subtract, '*' multiply, '/' divide,",
                "'sin' sine, 'cos' cosine', 'tan' tangent,",
                "'^' power, 'sqrt' calculate root",
                "Then enter another number.",
                "After calculations enter \"C\" to clear the result",
                "or \"Q\" to exit",
                "-----------------------------"
        );
    }
}

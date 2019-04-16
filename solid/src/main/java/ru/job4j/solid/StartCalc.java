package ru.job4j.solid;

import ru.job4j.calculator.Calculator;

/**
 * @author shaplov
 * @since 16.04.2019
 */
public class StartCalc {

    /**
     * Start program.
     */
    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc(
                new Calculator(), System.out, System.in
        );
        calc.showHelp();
        calc.init();
    }
}

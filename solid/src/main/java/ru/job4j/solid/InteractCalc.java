package ru.job4j.solid;

import ru.job4j.calculator.Calculator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Calculator which interacts with the user.
 * @author shaplov
 * @since 16.04.2019
 */
public class InteractCalc {

    private final PrintStream output;
    private final Calculator calc;
    private final Scanner in;

    private final Map<String, Consumer<Double>> actions = new HashMap<>();

    private boolean emptyResult = true;
    private boolean exit = false;

    /**
     * Constructor.
     * @param calc Calculator.
     * @param output PrintStream output.
     * @param in InputStream in.
     */
    public InteractCalc(final Calculator calc, final PrintStream output, final InputStream in) {
        this.calc = calc;
        this.output = output;
        this.in = new Scanner(in);
        this.in.useLocale(Locale.US);
    }

    /**
     * Main program loop.
     */
    public void init() {
        fillActions();
        do {
            act();
        } while (!this.exit);
    }

    /**
     * Executes chosen action.
     */
    private void act() {
        double result;
        if (this.emptyResult) {
            result = getNextDouble();
        } else {
            result = this.calc.getResult();
        }
        String key;
        boolean isKey = false;
        do {
            key = in.next().toUpperCase();
            if (this.actions.containsKey(key)) {
                this.emptyResult = false;
                this.actions.get(key).accept(result);
                isKey = true;
            } else {
                output.println("Enter a key from menu.");
            }
        } while (!isKey);
    }

    /**
     * Fills actions map.
     */
    private void fillActions() {
        this.actions.put("+", result -> {
                    this.calc.add(result, getNextDouble());
                    output.println(this.calc.getResult());
                }
        );
        this.actions.put("-", result -> {
                    this.calc.subtract(result, getNextDouble());
                    output.println(this.calc.getResult());
                }
        );
        this.actions.put("*", result -> {
                    this.calc.multiple(result, getNextDouble());
                    output.println(this.calc.getResult());
                }
        );
        this.actions.put("/", result -> {
                    this.calc.div(result, getNextDouble());
                    output.println(this.calc.getResult());
                }
        );
        this.actions.put("C", result -> {
                    this.emptyResult = true;
                    output.println("Result reset");
                }
        );
        this.actions.put("Q", result -> this.exit = true);
    }

    /**
     * Method for adding new functions
     * by subclasses.
     *
     * Functions with single argument.
     */
    protected void addAction(String key, Consumer<Double> consumer) {
        this.actions.put(key, result -> {
            consumer.accept(result);
            output.println(this.calc.getResult());
        });
    }

    /**
     * Method for adding new functions
     * by subclasses.
     *
     * Functions with double argument.
     */
    protected void addAction(String key, BiConsumer<Double, Double> consumer) {
        this.actions.put(key, result -> {
            consumer.accept(result, getNextDouble());
            output.println(this.calc.getResult());
        });
    }

    /**
     * Shows help text.
     */
    public void showHelp() {
        String help = buildMenu();
        output.println(help);
    }

    protected String buildMenu() {
        return String.join(System.lineSeparator(),
                "-----------------------------",
                "This is interactive calculator.",
                "Enter the number and chose",
                "one of the following actions",
                "by pressing the specified keys:",
                "'+' add, '-' subtract, '*' multiply, '/' divide.",
                "Then enter another number.",
                "After calculations enter \"C\" to clear the result",
                "or \"Q\" to exit",
                "-----------------------------"
        );
    }

    /**
     * Gets double value in loop.
     * @return double value.
     */
    protected double getNextDouble() {
        boolean valid = false;
        double result = 0.0;
        do {
            if (in.hasNextDouble()) {
                result = in.nextDouble();
                valid = true;
            } else {
                output.println("Please enter correct number using '.' as the delimiter");
                in.next();
            }
        } while (!valid);
        return result;
    }
}

package ru.job4j.calculator;

/**
 * Class Calculator решение задачи части 001 урок 3.1.
 * @author shaplov
 * @since 04.12.2018
 */
public class Calculator {

    private double result;

    /**
     * Method add.
     * @param first - first argument.
     * @param second - second argument.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     * @param first - first argument.
     * @param second - second argument.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method div.
     * @param first - first argument.
     * @param second - second argument.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method multiple.
     * @param first - first argument.
     * @param second - second argument.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }


    /**
     * Method multiple.
     * @return result.
     */
    public double getResult() {
        return this.result;
    }
}

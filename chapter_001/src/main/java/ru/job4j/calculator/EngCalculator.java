package ru.job4j.calculator;

public class EngCalculator extends Calculator {

    public void sine(double value) {
        value = Math.toRadians(value);
        double result = (double) Math.round(Math.sin(value) * 100000d) / 100000d;
        this.add(0, result);
    }

    public void cosine(double value) {
        value = Math.toRadians(value);
        double result = (double) Math.round(Math.cos(value) * 100000d) / 100000d;
        this.add(0, result);
    }

    public void tangent(double value) {
        value = Math.toRadians(value);
        double result = (double) Math.round(Math.tan(value) * 100000d) / 100000d;
        this.add(0, result);
    }

    public void power(double first, double second) {
        double result = Math.pow(first, second);
        this.add(0, result);
    }

    public void sqrt(double value) {
        double result = Math.sqrt(value);
        this.add(0, result);
    }
}

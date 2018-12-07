package ru.job4j.loop;

/**
 * Класс вычисления
 * факториала.
 * @author shaplov
 * @version 1.0
 * @since 07.12.2018
 */
public class Factorial {

    /**
     * Подсчет факториала
     * @param n задается
     * @return факториал
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

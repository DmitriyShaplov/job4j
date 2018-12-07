package ru.job4j.loop;

/**
 * Класс счетчика
 * @author shaplov
 * @since 07.12.2018
 */
public class Counter {

    /**
     * Вычисляет сумму четных
     * чисел в промежутке от start до finish.
     * @param start начальное значение счетчика
     * @param finish конечное значение
     * @return сумма четных чисел промежутка
     */
    public int add(int start, int finish) {
        int result = 0;
        while (start <= finish) {
            if (start % 2 == 0) {
                result += start;
            }
            start++;
        }
        return result;
    }
}

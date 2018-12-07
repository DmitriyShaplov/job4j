package ru.job4j.max;

/**
 * Определение максимального числа.
 */
public class Max {
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Вычисление максимального из трёх чисел.
     * @param first инт
     * @param second инт
     * @param third инт
     * @return максимальное число
     */
    public int max(int first, int second, int third) {
        int temp = this.max(first, second);
        temp = this.max(temp, third);
        return temp;
    }
}

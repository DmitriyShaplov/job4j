package ru.job4j.array;

/**
 * Класс для создания массива
 * с элементами в квадрате.
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class Square {

    /**
     * Возвращает массив с элементами
     * возвещенными в квадрат
     * @param bound количество элементов
     * @return заполеннный массив квадратами чисел
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 1; i <= rst.length; i++) {
            rst[i - 1] = i * i;
        }
        return rst;
    }
}

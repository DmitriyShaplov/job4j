package ru.job4j.array;

/**
 * @author shaplov
 * @version 1.00
 * @since 08.12.2018
 */
public class Check {

    /**
     * Проверяет, все ли элементы массива
     * логически одинаковые
     * @param data массив boolean
     * @return result false/true
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

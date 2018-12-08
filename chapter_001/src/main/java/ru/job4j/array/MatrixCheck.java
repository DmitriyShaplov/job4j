package ru.job4j.array;

/**
 * @author shaplov
 * @version 1.02 08.12.2018
 */
public class MatrixCheck {

    /**
     * Проверяет элементы диагоналей матрицы
     * на соответствие
     * @param data матрица
     * @return boolean result
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        int size = data.length;
        for (int i = 1; i < size; i++) {
            if ((data[i - 1][i - 1] != data[i][i])
                    || data[i - 1][size - i] != data[i][size - i - 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

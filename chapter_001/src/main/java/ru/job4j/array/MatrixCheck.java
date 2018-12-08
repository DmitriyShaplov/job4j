package ru.job4j.array;

/**
 * @author shaplov
 * @version 1.00 08.12.2018
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
        for (int i = 1, j = 1; i < size; i++, j++) {
            if ((data[i - 1][j - 1] != data[i][j]) ||
            data[i - 1][size - j] != data[i][size - j - 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}

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
        for (int i = 1, j = 1; i < data.length; i++, j++) {
            if (data[i - 1][j - 1] != data[i][j]) {
                result = false;
            }
        }
        for (int i = 1, j = data.length - 2; i < data.length; i++, j--) {
            if (data[i - 1][j + 1] != data[i][j]) {
                result = false;
            }
        }
        if (data.length % 2 != 0) {
            int size = data.length;
            if (data[0][0] != data[0][size - 1]) {
                result = false;
            }
        }
        return result;
    }
}

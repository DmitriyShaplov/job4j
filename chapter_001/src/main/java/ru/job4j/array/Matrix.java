package ru.job4j.array;

/**
 * Класс с матрицей.
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class Matrix {

    /**
     * метод создает матрицу в виде таблицы
     * умножения
     * @param size размер таблицы
     * @return таблица умножения
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}

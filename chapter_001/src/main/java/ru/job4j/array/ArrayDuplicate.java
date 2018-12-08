package ru.job4j.array;

import java.util.Arrays;

/**
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class ArrayDuplicate {

    /**
     * Убирает все повторяющиеся элементы
     * @param array входной массив
     * @return массив без повторений
     */
    public String[] remove(String[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (array[i].equals(array[j])) {
                    String temp = array[size - 1];
                    array[size - 1] = array[j];
                    array[j] = temp;
                    size--;
                    if (array[i].equals(array[j])) {
                        temp = array[size - 1];
                        array[size - 1] = array[j];
                        array[j] = temp;
                        size--;
                    }
                }
            }
        }
        return Arrays.copyOf(array, size);
    }
}

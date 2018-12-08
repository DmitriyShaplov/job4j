package ru.job4j.array;

/**
 * Класс сортировки пузырьком.
 * @author shaplov
 * @version 2.0 08.12.2018
 */
public class BubbleSort {

    /**
     * Сортирует массив алгоритмом пузырьком
     * @param array входной массив
     * @return array отсортированный массив
     */
    public int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }
}

package ru.job4j.array;

/**
 * Turn array.
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class Turn {

    /**
     * Turn array
     * @param array array
     * @return turned array
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }
}

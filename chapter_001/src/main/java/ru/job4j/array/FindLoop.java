package ru.job4j.array;

/**
 * Реализация поиска элемента в массиве.
 * @author shaplov
 * @version 1.00 08.12.2018
 */
public class FindLoop {

    /**
     * Searching int el in array
     * @param data array int
     * @param el element
     * @return index of element or -1
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}
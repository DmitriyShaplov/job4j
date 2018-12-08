package ru.job4j.array;

/**
 * Обертка над строкой.
 * @author shaplov
 * @version 1.00
 * @since 08.12.2018
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет, начинается ли слово с префикса
     * @param prefix префикс
     * @return result true/false
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        if (value.length > data.length) {
            result = false;
        } else {
            for (int i = 0; i < value.length; i++) {
                if (value[i] != data[i]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}

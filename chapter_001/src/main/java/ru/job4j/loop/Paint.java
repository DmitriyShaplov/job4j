package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Класс прорисовки пирамиды из ^.
 * @author shaplov
 * @version 1.0
 * @since 07.12.2018
 */
public class Paint {

    /**
     * прорисовка правой пирамиды
     * @param height высота
     * @return строка
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * прорисовка левой пирамиды
     * @param height высота
     * @return строка
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * прорисовка всей пирамиды
     * @param height высота
     * @return строка
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * условие проверки, когда нужно поставить галку
     * @param height высота
     * @return строка
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}

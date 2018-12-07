package ru.job4j.loop;

/**
 * Класс прорисовки доски из символов
 * @author shaplov
 * @version 1.00
 * @since 07.12.2018
 */
public class Board {

    /**
     * Метод прорисовки доски
     * крест накрест
     * @param width ширина доски
     * @param height высота доски
     * @return строчка из символов из "Х" и " "
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}

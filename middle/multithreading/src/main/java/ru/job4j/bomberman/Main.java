package ru.job4j.bomberman;

import ru.job4j.bomberman.enums.GameLevel;

/**
 * @author shaplov
 * @since 22.05.2019
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Game(GameLevel.HARD, 10);
            game.start();
        }
}

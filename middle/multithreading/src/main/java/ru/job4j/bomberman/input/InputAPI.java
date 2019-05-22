package ru.job4j.bomberman.input;

import ru.job4j.bomberman.enums.Direction;
import ru.job4j.bomberman.units.Hero;

/**
 * @author shaplov
 * @since 24.05.2019
 */
public class InputAPI {

    private final Hero hero;

    public InputAPI(Hero hero) {
        this.hero = hero;
    }

    public void goRight() {
        hero.setDirection(Direction.RIGHT);
    }

    public void goLeft() {
        hero.setDirection(Direction.LEFT);
    }

    public void goUp() {
        hero.setDirection(Direction.UP);
    }

    public void goDown() {
        hero.setDirection(Direction.DOWN);
    }
}

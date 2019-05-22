package ru.job4j.bomberman.units;

import ru.job4j.bomberman.Cell;
import ru.job4j.bomberman.enums.Direction;

/**
 * Class for hero status.
 *
 * @author shaplov
 * @since 24.05.2019
 */
public class Hero extends Unit {

    private volatile Direction direction = Direction.RIGHT;

    public Hero(Cell cell, String name) {
        super(cell, name);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction direction() {
        return direction;
    }
}

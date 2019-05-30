package ru.job4j.bomberman.units;

import ru.job4j.bomberman.Cell;

/**
 * @author shaplov
 * @since 22.05.2019
 */
public class Unit {

    private volatile Cell cell;
    private String name;

    public Unit(Cell cell, String name) {
        this.cell = cell;
        this.name = name;
    }

    public Cell getCell() {
        return cell;
    }

    public String getName() {
        return name;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}

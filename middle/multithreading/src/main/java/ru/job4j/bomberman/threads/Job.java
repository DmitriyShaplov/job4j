package ru.job4j.bomberman.threads;

import ru.job4j.bomberman.Board;
import ru.job4j.bomberman.Cell;
import ru.job4j.bomberman.units.Unit;

/**
 * @author shaplov
 * @since 24.05.2019
 */
public abstract class Job implements Runnable {

    protected final Unit unit;

    protected final Board board;

    public Job(Board board, Unit unit) {
        this.board = board;
        this.unit = unit;
    }

    protected boolean tryMove(int deltaX, int deltaY) throws InterruptedException {
        Cell dest = new Cell(unit.getCell().getRow() + deltaX, unit.getCell().getCol() + deltaY);
        boolean result = board.move(unit.getCell(), dest);
        if (result) {
            unit.setCell(dest);
        }
        return result;
    }
}

package ru.job4j.tictactoe.logics;

import ru.job4j.tictactoe.Cell;
import ru.job4j.tictactoe.Logic;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class SimpleLogic implements Logic {

    private Cell[][] cells;

    public SimpleLogic() {
    }

    @Override
    public void loadBoard(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public int size() {
        return this.cells.length;
    }

    @Override
    public boolean isWinnerX() {
        return isWinner(Cell::hasMarkX);
    }

    @Override
    public boolean isWinnerO() {
        return isWinner(Cell::hasMarkO);
    }

    private boolean isWinner(Predicate<Cell> winner) {
        boolean result = false;
        int size = this.cells.length;
        if (checkWinner(winner, 0, 0, 1, 1)
                || checkWinner(winner, 0, size - 1, 1, -1)) {
            result = true;
        }
        for (int index = 0; index < size && !result; ++index) {
            if (checkWinner(winner, index, 0, 0, 1)
                    || checkWinner(winner, 0, index, 1, 0)) {
                result = true;
            }
        }
        return result;
    }

    private boolean checkWinner(Predicate<Cell> winner, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        int size = this.cells.length;
        for (int index = 0; index < size; ++index) {
            Cell cell = cells[startX][startY];
            if (!winner.test(cell)) {
                result = false;
                break;
            }
            startX += deltaX;
            startY += deltaY;
        }
        return result;
    }

    @Override
    public boolean allFilled() {
        return Arrays.stream(cells).flatMap(Arrays::stream).noneMatch(Cell::isEmpty);
    }

    @Override
    public boolean select(int row, int column, boolean markX) {
        if (!this.cells[row][column].isEmpty()) {
            System.out.println("Cell is already taken");
            return false;
        }
        this.cells[row][column].take(markX);
        return true;
    }
}

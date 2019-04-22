package ru.job4j.tictactoe.console;

import ru.job4j.tictactoe.BoardCreator;
import ru.job4j.tictactoe.Cell;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class ConsoleBoardCreator implements BoardCreator {

    private int size = 3;

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Cell[][] createBoard() {
        Cell[][] cells = new Cell[size][size];
        for (int row = 0; row < cells.length; ++row) {
            for (int col = 0; col < cells.length; ++col) {
                cells[row][col] = new ConsoleCell();
            }
        }
        return cells;
    }
}

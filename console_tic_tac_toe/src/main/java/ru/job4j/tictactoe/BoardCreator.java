package ru.job4j.tictactoe;

import ru.job4j.tictactoe.Cell;

public interface BoardCreator {

    /**
     * set board size.
     * @param size int size.
     */
    void setSize(int size);

    /**
     * @return current board size;
     */
    Cell[][] createBoard();
}

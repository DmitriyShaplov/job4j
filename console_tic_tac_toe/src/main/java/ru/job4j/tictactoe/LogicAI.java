package ru.job4j.tictactoe;

public interface LogicAI {

    /**
     * Loads new board.
     */
    void loadBoard(Cell[][] cells);

    /**
     * Which side AI plays.
     * @param markX is comp on X mark.
     */
    void setMarkX(boolean markX);

    /**
     * calculates next move and make it.`
     */
    boolean makeMove();
}

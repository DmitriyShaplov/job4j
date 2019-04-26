package ru.job4j.tictactoe;

public interface Logic {

    /**
     * Loads new board.
     */
    void loadBoard(Cell[][] cells);

    /**
     * @return current board size;
     */
    int size();

    /**
     * @return is winner X.
     */
    boolean isWinnerX();

    /**
     * @return is winner X.
     */
    boolean isWinnerO();

    /**
     * @return if all cells filled.
     */
    boolean allFilled();

    /**
     * Put mark on board.
     * @param row row.
     * @param column col.
     * @param markX which mark.
     * @return success.
     */
    boolean select(int row, int column, boolean markX);
}

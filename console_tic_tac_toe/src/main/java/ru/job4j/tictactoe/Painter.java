package ru.job4j.tictactoe;

public interface Painter {

    /**
     * Loads new board.
     */
    void loadBoard(Cell[][] cells);

    /**
     * draw board in console.
     */
    void drawBoard();

    /**
     * Draw congratulates.
     * @param xWinner is X winner
     */
    void drawCongratulate(boolean xWinner);

    /**
     * When player moves.
     */
    void drawPlayerMove();

    /**
     * When computer moves.
     */
    void drawComputerMove();

    /**
     * Draw help options.
     */
    void drawHelp();

    /**
     * Draw if it's tie.
     */
    void drawTie();

    /**
     * When game starts.
     */
    void drawStartGame();
}

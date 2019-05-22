package ru.job4j.bomberman;

import ru.job4j.bomberman.enums.GameLevel;
import ru.job4j.bomberman.threads.EndGameThread;
import ru.job4j.bomberman.units.Hero;
import ru.job4j.bomberman.units.Unit;

import java.util.concurrent.ExecutionException;

/**
 * @author shaplov
 * @since 24.05.2019
 */
public class Game {

    /**
     * Difficulty level.
     */
    private final GameLevel level;

    /**
     * Board size.
     */
    private final int size;

    /**
     * Default constructor.
     * @param level Difficulty.
     * @param size board size.
     */
    public Game(final GameLevel level, final int size) {
        this.level = level;
        this.size = size;
    }

    /**
     * Inits classes and start threads.
     */
    public void start() {
        Hero hero = new Hero(new Cell(0, 0), "Hero");
        Board board = new Board(size, hero);
        putBlocks(board);
        ThreadsControl control = new ThreadsControl(monsterNumber(), board, hero);
        control.init();
        EndGameThread endGame = new EndGameThread(board, control);
        endGame.start();
        try {
            control.waitJobs();
            endGame.join();
        } catch (ExecutionException | InterruptedException e) {
            control.close();
            e.printStackTrace();
        }
    }

    /**
     * Put blocks on board.
     * @param board board.
     */
    private void putBlocks(Board board) {
        if (GameLevel.MEDIUM.equals(level) || GameLevel.HARD.equals(level)) {
            board.placeOnBoard(new Unit(new Cell(1, 1), "Block"));
            board.placeOnBoard(new Unit(new Cell(board.size() - 2, 1), "Block"));
            board.placeOnBoard(new Unit(new Cell(1, board.size() - 2), "Block"));
            board.placeOnBoard(new Unit(new Cell(board.size() - 2, board.size() - 2), "Block"));
        }
        if (GameLevel.HARD.equals(level)) {
            board.placeOnBoard(new Unit(new Cell(board.size() / 2, board.size() / 2), "Block"));
            board.placeOnBoard(new Unit(new Cell(board.size() / 2 + 1, board.size() / 2 + 1), "Block"));
            board.placeOnBoard(new Unit(new Cell(board.size() / 2 - 1, board.size() / 2 - 1), "Block"));
            board.placeOnBoard(new Unit(new Cell(board.size() / 2 - 1, board.size() / 2 + 1), "Block"));
            board.placeOnBoard(new Unit(new Cell(board.size() / 2 + 1, board.size() / 2 - 1), "Block"));
        }
    }

    /**
     * @return monster number
     * depending on the difficulty level.
     */
    private int monsterNumber() {
        final int monsterNumber;
        if (GameLevel.EASY.equals(level)) {
            monsterNumber = 2;
        } else if (GameLevel.MEDIUM.equals(level)) {
            monsterNumber = 3;
        } else if (GameLevel.HARD.equals(level)) {
            monsterNumber = 5;
        } else {
            monsterNumber = 0;
        }
        return monsterNumber;
    }
}

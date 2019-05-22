package ru.job4j.bomberman;

import net.jcip.annotations.GuardedBy;
import ru.job4j.bomberman.units.Hero;
import ru.job4j.bomberman.units.Unit;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shaplov
 * @since 22.05.2019
 */
public class Board {

    private final ReentrantLock[][] board;

    private final Hero hero;

    @GuardedBy("this")
    private volatile boolean endGame = false;

    private final int size;

    /**
     * Constructor.
     * @param size board size.
     */
    public Board(int size, Hero hero) {
        board = new ReentrantLock[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = new ReentrantLock();
            }
        }
        this.size = size;
        this.hero = hero;
    }

    /**
     * Moves unit from source to dest Cell.
     * @param source Cell.
     * @param dest Cell.
     * @return success.
     * @throws InterruptedException possible exception.
     */
    public boolean move(Cell source, Cell dest) throws InterruptedException {
        boolean result = false;
        if (dest.getRow() < board.length && dest.getRow() >= 0
                && dest.getCol() < board[dest.getRow()].length && dest.getCol() >= 0) {
            if (!"Hero thread".equals(Thread.currentThread().getName()) && dest.equals(hero.getCell())) {
                endGame();
            }
            result = board[dest.getRow()][dest.getCol()].tryLock(500, TimeUnit.MILLISECONDS);
            if (result) {
                board[source.getRow()][source.getCol()].unlock();
            } else {
                System.out.println("Cell locked! " + Thread.currentThread().getName());
            }
        }
        return result;
    }

    /**
     * Monster catches hero.
     */
    private synchronized void endGame() {
        endGame = true;
        System.out.println("Hero died.");
        notifyAll();
    }

    public synchronized boolean isEndGame() throws InterruptedException {
        System.out.println("Ending " + Thread.currentThread().getName());
        if (!endGame) {
            wait();
        }
        return endGame;
    }

    public int size() {
        return size;
    }

    /**
     * Place unit on board.
     * @param unit Unit.
     */
    public void placeOnBoard(Unit unit) {
        board[unit.getCell().getRow()][unit.getCell().getCol()].lock();
    }
}

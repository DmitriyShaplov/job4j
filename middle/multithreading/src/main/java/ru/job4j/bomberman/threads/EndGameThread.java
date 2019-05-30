package ru.job4j.bomberman.threads;

import ru.job4j.bomberman.Board;
import ru.job4j.bomberman.ThreadsControl;

/**
 * @author shaplov
 * @since 24.05.2019
 */
public class EndGameThread extends Thread {

    private final Board board;

    private final ThreadsControl control;

    public EndGameThread(Board board, ThreadsControl control) {
        this.board = board;
        this.control = control;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (board.isEndGame()) {
                    Thread.currentThread().interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        control.close();
    }
}

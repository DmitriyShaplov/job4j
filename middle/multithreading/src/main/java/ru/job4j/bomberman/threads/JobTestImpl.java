package ru.job4j.bomberman.threads;

import ru.job4j.bomberman.Board;
import ru.job4j.bomberman.units.Unit;

import java.util.Random;

/**
 * @author shaplov
 * @since 27.05.2019
 */
public class JobTestImpl extends Job {
    public JobTestImpl(Board board, Unit unit) {
        super(board, unit);
    }

    @Override
    public void run() {
        board.placeOnBoard(unit);
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (tryMove(0, -1)) {
                    System.out.println(unit.getCell() + " " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

package ru.job4j.bomberman.threads;

import ru.job4j.bomberman.Board;
import ru.job4j.bomberman.units.Hero;

/**
 * @author shaplov
 * @since 24.05.2019
 */
public class HeroJob extends Job {

    public HeroJob(Board board, Hero hero) {
        super(board, hero);
    }

    @Override
    public void run() {
        board.placeOnBoard(unit);
        Thread.currentThread().setName("Hero thread");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (directionMove()) {
                    System.out.println(unit.getCell() + " " + Thread.currentThread().getName() + " " + unit.getName());
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private boolean directionMove() throws InterruptedException {
        final boolean result;
        switch (((Hero) unit).direction()) {
            case LEFT:
                result = tryMove(0, -1);
                break;
            case DOWN:
                result = tryMove(1, 0);
                break;
            case UP:
                result = tryMove(-1, 0);
                break;
            case RIGHT:
            default:
                result = tryMove(0, 1);
                break;
        }
        return result;
    }
}

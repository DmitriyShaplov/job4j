package ru.job4j.bomberman.threads;

import javafx.util.Pair;
import ru.job4j.bomberman.Board;
import ru.job4j.bomberman.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author shaplov
 * @since 22.05.2019
 */
public class MonsterJob extends Job {

    public MonsterJob(Board board, Unit unit) {
        super(board, unit);
    }

    @Override
    public void run() {
        board.placeOnBoard(unit);
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(0, 1));
        pairs.add(new Pair<>(1, 0));
        pairs.add(new Pair<>(0, -1));
        pairs.add(new Pair<>(-1, 0));
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int variant = new Random().nextInt(4);
                Pair<Integer, Integer> pair = pairs.get(variant);
                if (tryMove(pair.getKey(), pair.getValue())) {
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

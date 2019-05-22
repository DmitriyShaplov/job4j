package ru.job4j.bomberman;

import ru.job4j.bomberman.threads.HeroJob;
import ru.job4j.bomberman.threads.MonsterJob;
import ru.job4j.bomberman.units.Hero;
import ru.job4j.bomberman.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author shaplov
 * @since 24.05.2019
 */
public class ThreadsControl {

    private final ExecutorService pool;

    private final Board board;

    private final Hero hero;

    private final int monsterNumber;

    private final List<Future<?>> futures = new ArrayList<>();

    public ThreadsControl(int monsterNumber, Board board, Hero hero) {
        pool = Executors.newFixedThreadPool(monsterNumber + 1);
        this.board = board;
        this.monsterNumber = monsterNumber;
        this.hero = hero;
    }

    public void init() {
        futures.clear();
        for (int i = 0; i < monsterNumber; i++) {
            futures.add(pool.submit(new MonsterJob(board, new Unit(new Cell(board.size() - 1, board.size() - 1), "Monster"))));
        }
        futures.add(pool.submit(new HeroJob(board, hero)));
    }

    public void waitJobs() throws ExecutionException, InterruptedException {
        for (Future<?> future : futures) {
            future.get();
        }
    }

    public void close() {
        pool.shutdownNow();
    }
}

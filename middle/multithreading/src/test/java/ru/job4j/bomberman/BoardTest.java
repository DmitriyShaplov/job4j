package ru.job4j.bomberman;

import org.junit.Test;
import ru.job4j.bomberman.input.InputAPI;
import ru.job4j.bomberman.threads.HeroJob;
import ru.job4j.bomberman.threads.Job;
import ru.job4j.bomberman.threads.JobTestImpl;
import ru.job4j.bomberman.units.Hero;
import ru.job4j.bomberman.units.Unit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void whenHeroDies() throws InterruptedException {
        Hero hero = new Hero(new Cell(0, 0), "Hero");
        Board board = new Board(3, hero);
        InputAPI input = new InputAPI(hero);
        Job heroJob = new HeroJob(board, hero);
        Job testMonsterJob = new JobTestImpl(board, new Unit(new Cell(2, 2), "Monster"));
        input.goDown();
        Thread heroThread = new Thread(heroJob);
        Thread monsterThread = new Thread(testMonsterJob);
        heroThread.start();
        Thread.sleep(1000);
        monsterThread.start();
        Thread.sleep(3000);
        heroThread.interrupt();
        monsterThread.interrupt();
        assertTrue(board.isEndGame());
        Cell expected = new Cell(2, 0);
        assertThat(hero.getCell(), is(expected));
    }
}
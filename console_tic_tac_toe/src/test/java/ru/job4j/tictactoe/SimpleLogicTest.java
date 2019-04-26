package ru.job4j.tictactoe;

import org.junit.Test;
import ru.job4j.tictactoe.console.ConsoleBoardCreator;
import ru.job4j.tictactoe.logics.SimpleLogic;
import ru.job4j.tictactoe.logics.SimpleLogicAI;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleLogicTest {

    @Test
    public void whenSetSizeThenSizeChanges() {
        BoardCreator creator = new ConsoleBoardCreator();
        Logic logic = new SimpleLogic();
        logic.loadBoard(creator.createBoard());
        int oldSize = logic.size();
        creator.setSize(55);
        logic.loadBoard(creator.createBoard());
        int newSize = logic.size();
        assertThat(oldSize, is(3));
        assertThat(newSize, is(55));
    }

    @Test
    public void whenInitThenDefaultSettings() {
        BoardCreator creator = new ConsoleBoardCreator();
        Logic logic = new SimpleLogic();
        logic.loadBoard(creator.createBoard());
        assertThat(logic.size(), is(3));
    }

    @Test
    public void whenPlayerTryMoveInSameCellThenFalse() {
        BoardCreator creator = new ConsoleBoardCreator();
        Logic logic = new SimpleLogic();
        Cell[][] cells = creator.createBoard();
        logic.loadBoard(cells);
        var try1 = logic.select(2, 2, true);
        var try2 = logic.select(2, 2, true);
        assertTrue(try1);
        assertFalse(try2);
        assertTrue(cells[2][2].hasMarkX());
    }

    @Test
    public void whenCheckIsWinnerThenInitShouldResultAndTheWinnerThenFalseAndNullAfterInit() {
        BoardCreator creator = new ConsoleBoardCreator();
        Logic logic = new SimpleLogic();
        Cell[][] cells = creator.createBoard();
        logic.loadBoard(cells);
        logic.select(0, 0, true);
        logic.select(1, 1, true);
        logic.select(2, 2, true);
        assertTrue(logic.isWinnerX());
    }

    @Test
    public void whenComputerPreventWin() {
        BoardCreator creator = new ConsoleBoardCreator();
        Logic logic = new SimpleLogic();
        Cell[][] cells = creator.createBoard();
        logic.loadBoard(cells);
        LogicAI ai = new SimpleLogicAI();
        ai.loadBoard(cells);
        logic.select(0, 0, true);
        logic.select(1, 1, true);
        ai.setMarkX(false);
        ai.makeMove();
        assertTrue(cells[2][2].hasMarkO());
    }

    @Test
    public void whenComputerTryWinThenWinner() {
        BoardCreator creator = new ConsoleBoardCreator();
        Logic logic = new SimpleLogic();
        Cell[][] cells = creator.createBoard();
        logic.loadBoard(cells);
        LogicAI ai = new SimpleLogicAI();
        ai.loadBoard(cells);
        logic.select(0, 0, true);
        logic.select(1, 1, true);
        ai.setMarkX(true);
        ai.makeMove();
        assertTrue(logic.isWinnerX());
    }
}
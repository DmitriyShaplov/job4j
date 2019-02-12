package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.PawnBlack;
import ru.job4j.chess.firuges.black.QueenBlack;
import ru.job4j.chess.firuges.black.RookBlack;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class for testing tracker
 * @author shaplov
 * @since 08.01.2019
 */

public class LogicTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    private final Logic logic = new Logic();

    /**
     * replace the print stream on our ByteArray
     */
    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    /**
     * return system output stream
     */
    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    private void initializeTestForRook() {
        PawnBlack pawn = new PawnBlack(Cell.H5);
        RookBlack rook = new RookBlack(Cell.H8);
        this.logic.add(pawn);
        this.logic.add(rook);
    }

    /**
     * Test for rook
     */
    @Test
    public void whenMovesRookWrongWayThenImpossibleMove() {
        initializeTestForRook();
        boolean rst = logic.move(Cell.H8, Cell.G5);
        assertThat(rst, is(false));
        ImpossibleMoveException ime = new ImpossibleMoveException("Impossible move");
        String ln = System.lineSeparator();
        assertThat(mem.toString(), is(ime.toString() + ln));
    }

    /**
     * Test for rook
     */
    @Test
    public void whenMovesRookWithBlockOnWheyThenExceptionAnotherFigureOnTheWay() {
        initializeTestForRook();
        boolean rst = logic.move(Cell.H8, Cell.H1);
        assertThat(rst, is(false));
        OccupiedWayException owe = new OccupiedWayException("There is another figure on the way");
        String ln = System.lineSeparator();
        assertThat(mem.toString(), is(owe.toString() + ln));
    }

    /**
     * Test for rook
     */
    @Test
    public void whenMovesRookRightThenTrue() {
        initializeTestForRook();
        boolean rstVert = logic.move(Cell.H8, Cell.H6);
        boolean rstHoriz = logic.move(Cell.H6, Cell.D6);
        assertThat(rstVert & rstHoriz, is(true));
    }

    @Test
    public void whenMovesEmptyCellThenException() {
        boolean rst = logic.move(Cell.E2, Cell.D5);
        assertThat(rst, is(false));
        FigureNotFoundException fnfe = new FigureNotFoundException("There is no figure");
        String ln = System.lineSeparator();
        assertThat(mem.toString(), is(fnfe.toString() + ln));
    }

    /**
     * Tests for Queen
     */
    @Test
    public void whenMovesQueenThenResults() {
        PawnBlack pawn = new PawnBlack(Cell.E5);
        QueenBlack queen = new QueenBlack(Cell.E8);
        this.logic.add(pawn);
        this.logic.add(queen);
        boolean moveOneFalse = logic.move(Cell.E8, Cell.F6);
        boolean moveTwoFalse = logic.move(Cell.E8, Cell.E4);
        boolean moveThreeTrue = logic.move(Cell.E8, Cell.E6);
        boolean moveFourTrue = logic.move(Cell.E6, Cell.C4);
        assertThat(moveOneFalse, is(false));
        assertThat(moveTwoFalse, is(false));
        assertThat(moveThreeTrue, is(true));
        assertThat(moveFourTrue, is(true));
        ImpossibleMoveException ime = new ImpossibleMoveException("Impossible move");
        OccupiedWayException owe = new OccupiedWayException("There is another figure on the way");
        String ln = System.lineSeparator();
        assertThat(mem.toString(), is(new StringBuilder()
                .append(ime.toString())
                .append(ln)
                .append(owe.toString())
                .append(ln)
                .toString()));
    }
}

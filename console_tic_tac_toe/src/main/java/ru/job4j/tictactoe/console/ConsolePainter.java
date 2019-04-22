package ru.job4j.tictactoe.console;

import ru.job4j.tictactoe.Painter;
import ru.job4j.tictactoe.Cell;

import java.io.PrintStream;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class ConsolePainter implements Painter {

    private Cell[][] cells;

    private PrintStream out;

    public ConsolePainter(PrintStream out) {
        this.out = out;
    }

    @Override
    public void loadBoard(Cell[][] cells) {
        this.cells = cells;
    }

    @Override
    public void drawBoard() {
        this.out.println("-----------------------");
        for (int row = 0; row < cells.length; ++row) {
            for (int col = 0; col < cells.length; ++col) {
                this.cells[row][col].draw(out);
                if (col < cells[row].length - 1) {
                    this.out.print("|");
                }
            }
            this.out.println();
        }
        this.out.println("-----------------------");
    }

    @Override
    public void drawCongratulate(boolean xWinner) {
        this.out.println("End game.");
        if (xWinner) {
            this.out.println("X is winnner!");
        } else {
            this.out.println("O is winner");
        }
    }

    @Override
    public void drawPlayerMove() {
        drawBoard();
    }

    @Override
    public void drawComputerMove() {
        this.out.println("Computer moves.");
        drawBoard();
    }

    @Override
    public void drawHelp() {
        this.out.println("-------------------------------");
        this.out.println("Welcome to console tic-tac-toe.");
        this.out.println("Commands:");
        this.out.println("\t\"SIZE\" - to set size of the board (3 as default);");
        this.out.println("\t\"SIDE\" - to chose X or O side (X as default);");
        this.out.println("\t\"START\" - to start new game.");
        this.out.println("\t\"INIT\" - to init new game.");
        this.out.println("\t\"EXIT\" - to exit application.");
    }

    @Override
    public void drawTie() {
        this.out.println("End game.");
        this.out.println("It's a tie.");
    }

    @Override
    public void drawStartGame() {
        this.out.println("Game started.");
    }
}

package ru.job4j.tictactoe.console;

import ru.job4j.tictactoe.Cell;

import java.io.PrintStream;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class ConsoleCell implements Cell<PrintStream> {

    private boolean markX = false;

    private boolean markO = false;

    public ConsoleCell() {
    }

    @Override
    public boolean hasMarkX() {
        return this.markX;
    }

    @Override
    public boolean hasMarkO() {
        return this.markO;
    }

    @Override
    public boolean isEmpty() {
        return !(hasMarkX() || hasMarkO());
    }

    @Override
    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    @Override
    public void draw(PrintStream brush) {
        if (this.hasMarkX()) {
            brush.print("X");
        } else if (this.hasMarkO()) {
            brush.print("O");
        } else {
            brush.print(" ");
        }
    }
}

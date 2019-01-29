package ru.job4j.chess.firuges;

import ru.job4j.chess.ImpossibleMoveException;

public abstract class Figure {

    private final Cell position;

    public Figure(final Cell position) {
        this.position = position;
    }

    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    public Cell position() {
        return this.position;
    }

    public abstract Figure copy(Cell dest);
}

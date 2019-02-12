package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.ImpossibleMoveException;

/**
 *
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack extends Figure {

    public BishopBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] cells = Cell.values();
        int size = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[size];
        int deltaX = 8 * (dest.x - source.x) / size;
        int deltaY = (dest.y - source.y) / size;
        for (int step = 1; step - 1 < size; step++) {
            Cell test = cells[(source.x * 8 + source.y)];
            steps[step - 1] = cells[(source.x * 8 + source.y) + deltaX * step + deltaY * step];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    private boolean isDiagonal(Cell source, Cell dest) {
        return !source.equals(dest) && (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y));
    }
}

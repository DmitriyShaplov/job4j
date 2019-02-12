package ru.job4j.chess.firuges.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class KnightBlack extends Figure {

    public KnightBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isKnightsMove(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] cells = Cell.values();
        Cell[] steps = new Cell[1];
        steps[0] = dest;
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new KnightBlack(dest);
    }

    private boolean isKnightsMove(Cell source, Cell dest) {
        return (Math.abs(dest.x - source.x) == 2 & Math.abs(dest.y - source.y) == 1)
                || (Math.abs(dest.x - source.x) == 1 & Math.abs(dest.y - source.y) == 2);
    }
}

package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Figure {

    public PawnWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isBlackPawnMove(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] steps;
        if (dest.y - source.y == 1) {
            steps = new Cell[]{dest};
        } else {
            steps = new Cell[2];
            Cell[] cells = Cell.values();
            steps[0] = cells[(source.x * 8 + source.y) + 1];
            steps[1] = dest;
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }

    private boolean isBlackPawnMove(Cell source, Cell dest) {
        return dest.x == source.x && (dest.y  - 1 == source.y || (source.y == 1 && dest.y == 3));
    }
}

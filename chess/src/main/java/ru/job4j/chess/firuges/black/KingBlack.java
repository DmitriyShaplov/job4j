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
public class KingBlack extends Figure {

    public KingBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isKingMove(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }

    private boolean isKingMove(Cell source, Cell dest) {
        return !source.equals(dest) && (Math.abs(dest.x - source.x) <= 1 && Math.abs(dest.y - source.y) <= 1);
    }
}

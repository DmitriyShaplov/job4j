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
public class RookBlack extends Figure {

    public RookBlack(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isRockMove(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] cells = Cell.values();
        int size = Math.abs((dest.x - source.x) + (dest.y - source.y));
        Cell[] steps = new Cell[size];
        int deltaX = ((dest.x - source.x) * 8) / size;
        int deltaY = (dest.y - source.y) / size;
        for (int step = 1; step - 1 < size; step++) {
            steps[step - 1] = cells[(source.x * 8 + source.y) + deltaX * step + deltaY * step];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }

    private boolean isRockMove(Cell source, Cell dest) {
        return !source.equals(dest) && (source.x == dest.x || source.y == dest.y);
    }
}

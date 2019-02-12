package ru.job4j.chess.firuges.white;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QueenWhite extends Figure {

    public QueenWhite(final Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!isQueenMove(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        int size = dest.x - source.x != 0 ? Math.abs(dest.x - source.x) : Math.abs(dest.y - source.y);
        int deltaX = (dest.x - source.x) * 8 / size;
        int deltaY = (dest.y - source.y) / size;
        Cell[] cells = Cell.values();
        Cell[] steps = new Cell[size];
        for (int step = 1; step - 1 < size; step++) {
            steps[step - 1] = cells[(source.x * 8 + source.y) + deltaX * step + deltaY * step];
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenWhite(dest);
    }

    private boolean isQueenMove(Cell source, Cell dest) {
        return !source.equals(dest) && ((Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y))
                || (source.x == dest.x || source.y == dest.y));
    }
}

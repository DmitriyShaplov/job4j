package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * //TODO add comments.
 *
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {
            int index = this.findBy(source);
            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    if (checkWay(steps)) {
                        rst = true;
                        this.figures[index] = this.figures[index].copy(dest);
                    }
                }
            }
        } catch (ImpossibleMoveException | FigureNotFoundException | OccupiedWayException e) {
            System.out.println(e.toString());
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private boolean checkWay(Cell[] steps) throws OccupiedWayException {
        boolean res = Arrays.stream(this.figures).filter(Objects::nonNull)
                .anyMatch(
                f -> Arrays.stream(steps).anyMatch(
                            cell -> f.position().equals(cell)
                    )
        );
        if (res) {
            throw new OccupiedWayException("There is another figure on the way");
        }
        return true;
    }

    private int findBy(Cell cell) throws FigureNotFoundException {
        int rst = IntStream.range(0, this.figures.length)
                .filter(i -> this.figures[i] != null && figures[i].position().equals(cell))
                .findAny().orElse(-1);
        if (rst == -1) {
            throw new FigureNotFoundException("There is no figure");
        }
        return rst;
    }
}

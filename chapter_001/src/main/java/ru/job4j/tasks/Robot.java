package ru.job4j.tasks;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * @author shaplov
 * @since 03.07.2019
 */
public class Robot {

    /**
     * Searches min way in array.
     * @param board array consists of 0 and 1, Robot can go only through 1.
     * @param sx start x.
     * @param sy start y.
     * @param fx finish x.
     * @param fy finish y.
     * @return int min steps.
     */
    public int minWay(int[][] board, int sx, int sy, int fx, int fy) {
        int markCount = 1;
        Set<Point> points = new HashSet<>();
        points.add(new Point(sx, sy));
        while (board[fx][fy] == 1) {
            if (points.isEmpty()) {
                break;
            }
            Set<Point> newPoints = new HashSet<>();
            for (Point point : points) {
                board[point.x][point.y] += markCount;
                newPoints.addAll(getNewPoints(board, point));
            }
            points = newPoints;
            markCount++;
        }
        return board[fx][fy] - 2;
    }

    private Set<Point> getNewPoints(final int[][]board, final Point p) {
        final Set<Point> result = new HashSet<>();
        BiConsumer<Integer, Integer> consumer = (x, y) -> {
            if (x >= 0 && x < board.length
                    && y >= 0 && y < board[x].length
                    && board[x][y] == 1) {
                result.add(new Point(x, y));
            }
        };
        consumer.accept(p.x + 1, p.y);
        consumer.accept(p.x - 1, p.y);
        consumer.accept(p.x, p.y + 1);
        consumer.accept(p.x, p.y - 1);
        return result;
    }

    /**
     * Holds x and y coordinates for convenience.
     */
    private static class Point {
        private int x;
        private int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x
                    && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

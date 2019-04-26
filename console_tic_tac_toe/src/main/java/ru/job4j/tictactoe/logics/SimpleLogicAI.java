package ru.job4j.tictactoe.logics;

import javafx.util.Pair;
import ru.job4j.tictactoe.Cell;
import ru.job4j.tictactoe.LogicAI;

import java.util.*;
import java.util.function.Predicate;

/**
 * @author shaplov
 * @since 25.04.2019
 */
public class SimpleLogicAI implements LogicAI {

    private Cell[][] cells;
    private int size;

    private boolean markX = false;

    private Predicate<Cell> checkComp = Cell::hasMarkO;
    private Predicate<Cell> checkPlayer = Cell::hasMarkX;

    public SimpleLogicAI() {
    }

    @Override
    public void loadBoard(Cell[][] cells) {
        this.cells = cells;
        this.size = cells.length;
    }

    @Override
    public void setMarkX(boolean markX) {
        this.markX = markX;
        if (markX) {
            this.checkComp = Cell::hasMarkX;
            this.checkPlayer = Cell::hasMarkO;
        } else {
            this.checkComp = Cell::hasMarkX;
            this.checkPlayer = Cell::hasMarkO;
        }
    }

    /**
     * Computer moves logic.
     */
    @Override
    public boolean makeMove() {
        return compTryWin()
                || compTryPreventWin()
                || compTryBestMove()
                || compTryCenter()
                || compTryFreeLine()
                || compRandomMove();
    }

    /**
     * If only 1 move to win.
     */
    private boolean compTryWin() {
        return tryOneMove(checkComp, checkPlayer);
    }

    /**
     * If only 1 move to lose.
     */
    private boolean compTryPreventWin() {
        return tryOneMove(checkPlayer, checkComp);
    }

    private boolean tryOneMove(Predicate<Cell> toCount, Predicate<Cell> toCheck) {
        boolean result = false;
        if (oneMoveLeft(toCount, toCheck, 0, 0, 1, 1)
                || oneMoveLeft(toCount, toCheck, 0, size - 1, 1, -1)) {
            result = true;
        }
        for (int index = 0; index < this.size && !result; ++index) {
            if (oneMoveLeft(toCount, toCheck, index, 0, 0, 1)
                    || oneMoveLeft(toCount, toCheck, 0, index, 1, 0)) {
                result = true;
            }
        }
        return result;
    }

    private boolean oneMoveLeft(Predicate<Cell> toCount, Predicate<Cell> toCheck, int startX, int startY, int deltaX, int deltaY) {
        boolean result = false;
        boolean isNull = false;
        boolean isCheck = false;
        int counter = 0;
        int nullRow = -1;
        int nullCol = -1;
        for (int index = 0; index < this.size; ++index) {
            Cell cell = cells[startX][startY];
            if (toCheck.test(cell)) {
                isCheck = true;
                break;
            } else if (toCount.test(cell)) {
                ++counter;
            } else if (!isNull && cell.isEmpty()) {
                nullRow = startX;
                nullCol = startY;
                isNull = true;
            }
            startX += deltaX;
            startY += deltaY;
        }
        if (!isCheck && isNull && counter == this.size - 1) {
            cells[nullRow][nullCol].take(this.markX);
            result = true;
        }
        return result;
    }

    /**
     * Calculates greatest amount X or O in
     * one row / column / diagonal and continues to build line up
     * or prevent it for opponent or both.
     */
    @SuppressWarnings("Duplicates")
    private boolean compTryBestMove() {
        boolean result = false;
        var resultSet = tryBest(this.checkComp, this.checkPlayer);
        int maxCompCount = 0;
        Map<Pair<Integer, Integer>, Integer> cellsComp = null;
        Map<Pair<Integer, Integer>, Integer> cellsPlayer = null;
        if (!resultSet.isEmpty()) {
            maxCompCount = (Integer) resultSet.get("count");
            cellsComp = (Map<Pair<Integer, Integer>, Integer>) resultSet.get("cells");
        }
        resultSet = tryBest(this.checkPlayer, this.checkComp);
        int maxPlayerCount = 0;
        if (!resultSet.isEmpty()) {
            maxPlayerCount = (Integer) resultSet.get("count");
            cellsPlayer = (Map<Pair<Integer, Integer>, Integer>) resultSet.get("cells");
        }
        if (maxPlayerCount > 1 && maxPlayerCount > maxCompCount) {
            if (maxCompCount <= 0) {
                var pair = Collections.max(cellsPlayer.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
                this.cells[pair.getKey()][pair.getValue()].take(this.markX);
            } else {
                for (var entry : cellsComp.entrySet()) {
                    if (cellsPlayer.containsKey(entry.getKey())) {
                        cellsPlayer.put(entry.getKey(), cellsPlayer.get(entry.getKey()) + entry.getValue());
                    }
                }
                var pair = Collections.max(cellsPlayer.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
                this.cells[pair.getKey()][pair.getValue()].take(this.markX);
            }
            result = true;
        } else if (maxCompCount > 0) {
            if (maxPlayerCount <= 1) {
                var pair = Collections.max(cellsComp.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
                this.cells[pair.getKey()][pair.getValue()].take(this.markX);
            } else {
                for (var entry : cellsPlayer.entrySet()) {
                    if (cellsComp.containsKey(entry.getKey())) {
                        cellsComp.put(entry.getKey(), cellsComp.get(entry.getKey()) + entry.getValue());
                    }
                }
                var pair = Collections.max(cellsComp.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
                this.cells[pair.getKey()][pair.getValue()].take(this.markX);
            }
            result = true;
        }
        return result;
    }

    /**
     * Calculates amounts in rows/cols/diagonals and
     * put them in map.
     * @return string-integer map with result set.
     */
    private Map<String, Object> tryBest(Predicate<Cell> toCount, Predicate<Cell> toCheck) {
        Map<String, Object> result = new HashMap<>();
        bestMoveCalculate(toCount, toCheck, result, 0, 0, 1, 1);
        bestMoveCalculate(toCount, toCheck, result, 0, size - 1, 1, -1);
        for (int index = 0; index < size; ++index) {
            bestMoveCalculate(toCount, toCheck, result, index, 0, 0, 1);
            bestMoveCalculate(toCount, toCheck, result, 0, index, 1, 0);
        }
        return result;
    }

    private void bestMoveCalculate(Predicate<Cell> toCount, Predicate<Cell> toCheck, Map<String, Object> resultSet,
                                   int startX, int startY, int deltaX, int deltaY) {
        int count = 0;
        boolean isNull = false;
        boolean isCheck = false;
        Map<Pair<Integer, Integer>, Integer> cellMap = new LinkedHashMap<>();
        for (int index = 0; index < size; ++index) {
            Cell cell = cells[startX][startY];
            if (toCheck.test(cell)) {
                isCheck = true;
                break;
            } else if (cell.isEmpty()) {
                isNull = true;
                cellMap.put(new Pair<>(startX, startY), 1);
            } else if (toCount.test(cell)) {
                ++count;
            }
            startX += deltaX;
            startY += deltaY;
        }
        if (!isCheck && isNull && count > 0) {
            if (resultSet.isEmpty() || count > (Integer) resultSet.get("count")) {
                resultSet.put("count", count);
                resultSet.put("cells", cellMap);
            } else if (count == (Integer) resultSet.get("count")) {
                var map = (Map<Pair<Integer, Integer>, Integer>) resultSet.get("cells");
                for (var entry : cellMap.entrySet()) {
                    if (map.containsKey(entry.getKey())) {
                        map.put(entry.getKey(), map.get(entry.getKey()) + 1);
                    } else {
                        map.put(entry.getKey(), 1);
                    }
                }
                resultSet.put("count", count);
                resultSet.put("cells", map);
            }
        }
    }

    /**
     * Try to put his mark to center.
     */
    private boolean compTryCenter() {
        boolean result = false;
        if (this.size % 2 != 0) {
            if (this.cells[this.size / 2][this.size / 2].isEmpty()) {
                this.cells[this.size / 2][this.size / 2].take(this.markX);
                result = true;
            }
        } else {
            if (this.cells[this.size / 2 - 1][this.size / 2 - 1].isEmpty()) {
                this.cells[this.size / 2 - 1][this.size / 2 - 1].take(this.markX);
                result = true;
            } else if (this.cells[this.size / 2 - 1][this.size / 2].isEmpty()) {
                this.cells[this.size / 2 - 1][this.size / 2].take(this.markX);
                result = true;
            } else if (this.cells[this.size / 2][this.size / 2 - 1].isEmpty()) {
                this.cells[this.size / 2][this.size / 2 - 1].take(this.markX);
                result = true;
            } else if (this.cells[this.size / 2][this.size / 2].isEmpty()) {
                this.cells[this.size / 2][this.size / 2].take(this.markX);
                result = true;
            }
        }
        return result;
    }

    /**
     * Tries put on free line.
     */
    private boolean compTryFreeLine() {
        boolean result = false;
        if (tryFreeLine(0, 0, 1, 1) || tryFreeLine(0, size - 1, 1, -1)) {
            result = true;
        }
        for (int index = 0; index < size && !result; ++index) {
            if (tryFreeLine(index, 0, 0, 1) || tryFreeLine(0, index, 1, 0)) {
                result = true;
            }
        }
        return result;
    }

    private boolean tryFreeLine(int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index < size; ++index) {
            if (!cells[startX][startY].isEmpty()) {
                result = false;
                break;
            }
            startX += deltaX;
            startY += deltaY;
        }
        if (result) {
            startX -= deltaX;
            startY -= deltaY;
            cells[startX][startY].take(this.markX);
        }
        return result;
    }

    /**
     * Collects empty cells and moves
     * into random ones.
     */
    private boolean compRandomMove() {
        List<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int row = 0; row < this.size; ++row) {
            for (int col = 0; col < this.size; ++col) {
                if (this.cells[row][col].isEmpty()) {
                    Pair<Integer, Integer> pair = new Pair<>(row, col);
                    pairs.add(pair);
                }
            }
        }
        if (pairs.isEmpty()) {
            return false;
        }
        int p = (int) (Math.random() * pairs.size());
        int row = pairs.get(p).getKey();
        int col = pairs.get(p).getValue();
        this.cells[row][col].take(this.markX);
        return true;
    }
}

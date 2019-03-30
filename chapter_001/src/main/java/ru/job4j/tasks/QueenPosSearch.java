package ru.job4j.tasks;

import java.util.Scanner;

/**
 * Task about 8 (or more) Queens.
 * @author shaplov
 * @since 30.03.2019
 */
public class QueenPosSearch {

    private int variantCount;
    private int[][] board;
    private int size;

    /**
     * Checks if we can put a Queen at this cell.
     * @param i - column
     * @param j - row
     * @return result
     */
    private boolean checkPos(int i, int j) {
        boolean result = true;
        int cnt = 1;
        --j;
        for (; j >= 0; --j) {
            if (this.board[i][j] == 1
                    || (((i - cnt) >= 0) && this.board[i - cnt][j] == 1)
                    || (((i + cnt) < this.size) && this.board[i + cnt][j] == 1)) {
                result = false;
                break;
            }
            ++cnt;
        }
        return result;
    }

    /**
     * Tries to put a Queen in next cell.
     * @param j - next row.
     */
    private void fillArray(int j) {
        if (j == this.size) {
            ++this.variantCount;
            return;
        }
        for (int i = 0; i < this.size; ++i) {
            if (checkPos(i, j)) {
                this.board[i][j] = 1;
                fillArray(j + 1);
                this.board[i][j] = 0;
            }
        }
    }

    /**
     * Start search position variants function.
     * @return number of possible arrangements.
     */
    private int calculateNumber() {
        this.variantCount = 0;
        Scanner scanner = new Scanner(System.in);
        this.size = 8;
        if (scanner.hasNextInt()) {
            this.size = scanner.nextInt();
        }
        this.board = new int[size][size];
        fillArray(0);
        return this.variantCount;
    }

    /**
     * Main function.
     */
    public static void main(String[] args) {
        System.out.println(new QueenPosSearch().calculateNumber());
    }
}

package ru.job4j.tasks;

import java.util.Arrays;

/**
 * Class for implementation Coffee Machine methods
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class CoffeeMachine {
    private static final int[] CHANGE = {10, 5, 2, 1};
    private static final int[] BANKNOTES = {1, 2, 5, 10, 50, 100, 200, 500, 1000, 2000, 5000};

    /**
     * This method giving change in coins as an int array
     * @param value inserted note or coin
     * @param price coffee cost
     * @return array of coins
     */
    public static int[] changes(int value, int price) {
        boolean isBanknote = false;
        for (int money : BANKNOTES) {
            if (value == money) {
                isBanknote = true;
                break;
            }
        }
        int[] result;
        if (!isBanknote || value - price < 0) {
            result = new int[0];
        } else {
            int coinsAmoung = 0;
            int[] coins = new int[CHANGE.length];
            int remainder = value - price;
            for (int i = 0; i < CHANGE.length; i++) {
                if (remainder == 0) {
                    break;
                }
                coinsAmoung += remainder / CHANGE[i];
                coins[i] = remainder / CHANGE[i];
                remainder = remainder % CHANGE[i];
            }
            result = new int[coinsAmoung];
            int num = 0;
            for (int i = 0; i < CHANGE.length; i++) {
                for (int coin = 0; coin < coins[i]; coin++) {
                    result[num] = CHANGE[i];
                    num++;
                }
            }
        }
        return result;
    }
}

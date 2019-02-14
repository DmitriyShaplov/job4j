package ru.job4j.test;

import java.util.Arrays;

public class CoffeeMachine {
    private static final int[] CHANGE = {10, 5, 2, 1};
    private static final int[] BANKNOTES = {50, 100, 200, 500, 1000, 2000, 5000};

    public static int[] changes(int value, int price) {
        boolean isBanknote = false;
        for (int money : BANKNOTES) {
            if (value == money) {
                isBanknote = true;
                break;
            }
        }
        int [] result;
        if (!isBanknote) {
            result = new int[0];
        } else {
            int coinsAmoung = 0;
            int[] coins = new int[4];
            int remainder = value - price;
            for (int i = 0; i < 4; i++) {
                if (remainder == 0) {
                    break;
                }
                coinsAmoung += remainder / CHANGE[i];
                coins[i] = remainder / CHANGE[i];
                remainder = remainder % CHANGE[i];
            }
            result = new int[coinsAmoung];
            int num = 0;
            for (int i = 0; i < 4; i++) {
                for (int coin = 0; coin < coins[i]; coin++) {
                    result[num] = CHANGE[i];
                    num++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = CoffeeMachine.changes(100, 31);
        System.out.println(Arrays.toString(result));
    }
}

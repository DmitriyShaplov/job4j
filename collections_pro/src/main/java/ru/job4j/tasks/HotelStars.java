package ru.job4j.tasks;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author shaplov
 * @since 14.03.2019
 */
public class HotelStars {

    /**
     * This method returns int array of hotel stars
     * based in hotels rating.
     * For example input array: 99 2 100 50 1
     * Output array: 4 2 5 3 1
     * @param numbers int array of hotel rating.
     * @return int array of hotel stars.
     */
    public int[] makeStars(int ... numbers) {
        if (numbers.length % 5 != 0) {
            return new int[0];
        }
        Map<Integer, Integer> linkMap = new LinkedHashMap<>();
        for (int number : numbers) {
            linkMap.put(number, 0);
        }
        Arrays.sort(numbers);
        int index = 0;
        for (int star = 1; star <= 5; star++) {
            for (int cnt = 0; cnt < numbers.length / 5; cnt++) {
                linkMap.put(numbers[index++], star);
            }
        }
        int[] result = new int[numbers.length];
        index = 0;
        for (int num : linkMap.values()) {
            result[index++] = num;
        }
        return result;
    }
}

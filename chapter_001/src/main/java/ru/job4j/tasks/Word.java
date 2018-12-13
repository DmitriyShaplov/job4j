package ru.job4j.tasks;

/**
 * Класс с методами для строк.
 * @author shaplov
 * @since 13.12.2018
 */
public class Word {

    /**
     * Do these words differ only by one permutation?
     * @param first string
     * @param second string
     * @return boolean result
     */
    boolean isOnlyOnePermutation(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        int checkPos = -1;
        int checkTimes = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                if (checkTimes == 0) {
                    checkPos = i;
                    checkTimes++;
                } else if (checkTimes == 1) {
                    if (second.charAt(i) != first.charAt(checkPos)
                    || second.charAt(checkPos) != first.charAt(i)) {
                        return false;
                    }
                    checkTimes++;
                } else {
                    return false;
                }
            }
        }
        return checkTimes == 2;
    }
}

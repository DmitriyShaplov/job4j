package ru.job4j.tasks;

/**
 * @author shaplov
 * @since 13.12.18
 */
public class MergeArrays {

    /**
     * Merging two sorted arrays
     * @param first first int array
     * @param second second int array
     * @return result sorted array
     */
    public int[] mergeFunc(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int j = 0;
        int k = 0;
        int i = 0;
        while (j < first.length && k < second.length) {
            if (first[j] < second[k]) {
                result[i] = first[j];
                j++;
            } else {
                result[i] = second[k];
                k++;
            }
            i++;
        }
        while (j < first.length) {
            result[i] = first[j];
            j++;
            i++;
        }
        while (k < second.length) {
            result[i] = second[k];
            k++;
            i++;
        }
        return result;
    }
}

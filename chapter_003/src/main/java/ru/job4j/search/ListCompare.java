package ru.job4j.search;

import java.util.Comparator;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        final int minLen = o1.length() < o2.length() ? o1.length() : o2.length();
        int rs1 = 0;
        for (int index = 0; index < minLen; index++) {
            if (o1.charAt(index) != o2.charAt(index)) {
                rs1 = Character.compare(o1.charAt(index), o2.charAt(index));
                break;
            }
        }
        return rs1 != 0 ? rs1 : Integer.compare(o1.length(), o2.length());
    }
}

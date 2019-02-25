package ru.job4j.tasks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author shaplov
 * @version 0.1
 * @since 25.02.2019
 */
public class SortDepartments {
    /**
     * Sort list in descending order
     * @param list list
     * @return sorted list
     */
    public List<String> sortDes(List<String> list) {
        list = addMissing(list);
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                final int minLen = o1.length() < o2.length() ? o1.length() : o2.length();
                int rs1 = 0;
                for (int index = 0; index < minLen; index++) {
                    if (o1.charAt(index) != o2.charAt(index)) {
                        rs1 = Character.compare(o2.charAt(index), o1.charAt(index));
                        break;
                    }
                }
                return rs1 != 0 ? rs1 : Integer.compare(o1.length(), o2.length());
            }
        });
        return list;
    }

    /**
     * Sort list in ascending order
     * @param list list
     * @return sorted list
     */
    public List<String> sortAsc(List<String> list) {
        list = addMissing(list);
        Collections.sort(list);
        return list;
    }

    /**
     * Add missing elements
     * @param list list
     * @return complete list
     */
    private List<String> addMissing(List<String> list) {
        for (int index = 0; index < list.size(); index++) {
            String str = list.get(index);
            int pos = 0;
            while (str.indexOf("\\", pos) != -1) {
                pos = str.indexOf("\\", pos);
                if (!list.contains(str.substring(0, pos))) {
                    list.add(str.substring(0, pos));
                }
                pos++;
            }
        }
        return list;
    }
}

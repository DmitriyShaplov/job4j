package ru.job4j.tasks;

import java.util.ArrayList;
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
                int rs1 = Integer.compare(o1.length(), o2.length());
                if (o1.length() < o2.length()) {
                    o2 = o2.substring(0, o1.length());
                }
                if (o1.length() > o2.length()) {
                    o1 = o1.substring(0, o2.length() - 1);
                }
                int rs2 = o2.compareTo(o1);
                return rs2 != 0 ? rs2 : rs1;
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

package ru.job4j.tasks;

import java.util.Collections;
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
        addMissing(list);
        list.sort((o1, o2) -> {
            int rs1 = Integer.compare(o1.length(), o2.length());
            if (o1.length() < o2.length()) {
                o2 = o2.substring(0, o1.length());
            }
            if (o1.length() > o2.length()) {
                o1 = o1.substring(0, o2.length());
            }
            int rs2 = o2.compareTo(o1);
            return rs2 != 0 ? rs2 : rs1;
        });
        return list;
    }

    /**
     * Sort list in ascending order
     * @param list list
     * @return sorted list
     */
    public List<String> sortAsc(List<String> list) {
        addMissing(list);
        Collections.sort(list);
        return list;
    }

    /**
     * Add missing elements to list
     * @param list list
     */
    private void addMissing(List<String> list) {
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
    }
}

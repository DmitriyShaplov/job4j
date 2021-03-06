package ru.job4j.tasks;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for anagram task.
 * @author shaplov
 * @since 14.03.2019
 */
public class Anagram {

    public boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char c1 : s1.toCharArray()) {
            map1.put(c1, map1.containsKey(c1) ? map1.get(c1) + 1 : 1);
        }
        for (char c2 : s2.toCharArray()) {
            if (!map1.containsKey(c2)) {
                return false;
            }
            map2.put(c2, map2.containsKey(c2) ? map2.get(c2) + 1 : 1);
        }
        return map1.equals(map2);
    }
}

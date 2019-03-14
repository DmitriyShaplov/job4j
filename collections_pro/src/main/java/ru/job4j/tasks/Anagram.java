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
        Map<Character, Integer> map = new HashMap<>();
        boolean result = true;
        for (char c : s1.toCharArray()) {
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        for (char c : s2.toCharArray()) {
            if (map.get(c) == null || map.get(c) == 0) {
                result = false;
                break;
            }
            map.put(c, map.get(c) - 1);
        }
        for (int i : map.values()) {
            if (i != 0) {
                result = false;
                break;
            }
        }
        return result;
    }
}

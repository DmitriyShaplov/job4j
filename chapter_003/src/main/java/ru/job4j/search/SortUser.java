package ru.job4j.search;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Sorting User algorithms.
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class SortUser {
    /**
     * Sorting Users by age
     * @param users List of Users
     * @return sorted Set of Users
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }
}

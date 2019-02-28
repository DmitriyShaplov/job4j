package ru.job4j.search;

import java.util.*;

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

    /**
     * Sorting Users by Name's length
     * @param users List of Users
     * @return sorted List of Users
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(Comparator.comparingInt(
                 user -> user.getName().length())
        );
        return users;
    }

    /**
     * Sorting Users by Name ang Age
     * @param users List of Users
     * @return sorted List of Users
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));
        return users;
    }
}

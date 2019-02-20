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
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.compare(o1.getName().length(), o2.getName().length());
            }
        });
        return users;
    }

    /**
     * Sorting Users by Name ang Age
     * @param users List of Users
     * @return sorted List of Users
     */
    public List<User> sortByAllFields(List<User> users) {
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int rs1 = o1.getName().compareTo(o2.getName());
                return rs1 != 0 ? rs1 : Integer.compare(o1.getAge(), o2.getAge());
            }
        });
        return users;
    }
}

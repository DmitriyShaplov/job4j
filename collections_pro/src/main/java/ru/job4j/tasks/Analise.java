package ru.job4j.tasks;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * User analysis.
 * @author shaplov
 * @since 15.03.2019
 */
public class Analise {

    /**
     * Compares two lists of users.
     * Returns Info object that contains
     * the number of added, deleted, and
     * modified users.
     */
    public Info diff(List<User> previous, List<User> current) {
        Map<Integer, String> prvUserMap = previous.stream().collect(Collectors.toMap(k -> k.id, v -> v.name));
        Map<Integer, String> curUserMap = current.stream().collect(Collectors.toMap(k -> k.id, v -> v.name));
        int added = current.size() - (int) current.stream().filter(v -> prvUserMap.containsKey(v.id)).count();
        int deleted = previous.size() - (int) previous.stream().filter(v -> curUserMap.containsKey(v.id)).count();
        int changed = (int) prvUserMap.keySet().stream()
                .filter(curUserMap::containsKey)
                .filter(k -> !prvUserMap.get(k).equals(curUserMap.get(k))).count();
        return new Info(added, deleted, changed);
    }

    /**
     * User class.
     */
    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /**
     * Info class.
     */
    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int deleted, int changed) {
            this.added = added;
            this.deleted = deleted;
            this.changed = changed;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }
    }
}

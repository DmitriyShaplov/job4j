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
        int changed = 0;
        int cnt = 0;
        for (User user : current) {
            if (prvUserMap.containsKey(user.id)) {
                cnt++;
                if (prvUserMap.get(user.id) == null && user.name == null) {
                    continue;
                }
                if ((prvUserMap.get(user.id) == null && user.name != null)
                       || !prvUserMap.get(user.id).equals(user.name)) {
                    changed++;
                }
            }
        }
        var added = current.size() - cnt;
        var deleted = previous.size() - cnt;
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

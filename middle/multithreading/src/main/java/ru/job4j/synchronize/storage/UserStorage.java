package ru.job4j.synchronize.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shaplov
 * @since 17.05.2019
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private Map<Integer, User> users = new HashMap<>();

    /**
     * Default constructor.
     */
    public UserStorage() {
    }

    /**
     * Adds user to storage.
     * @param user User.
     * @return success.
     */
    public synchronized boolean add(User user) {
        boolean success = false;
        if (user != null && !users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            success = true;
        }
        return success;
    }

    /**
     * Updates user if exists.
     * @param user User.
     * @return success.
     */
    public synchronized boolean update(User user) {
        boolean success = false;
        if (user != null && users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            success = true;
        }
        return success;
    }

    /**
     * Deletes user.
     * @param user User.
     * @return success.
     */
    public synchronized boolean delete(User user) {
        boolean success = false;
        if (user != null && users.containsKey(user.getId())) {
            users.remove(user.getId());
            success = true;
        }
        return success;
    }

    /**
     * Transfers amount from one user to another.
     * @param fromId user's from id.
     * @param toId user's to id.
     * @param amount amount.
     * @return success.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean success = false;
        if (users.containsKey(fromId) && users.containsKey(toId)) {
            success = users.get(fromId).transfer(users.get(toId), amount);
        }
        return success;
    }
}

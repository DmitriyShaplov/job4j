package ru.job4j.search;

import java.util.HashMap;
import java.util.List;

/**
 * Class for Users operations.
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     * Processing List of Users Into HashMap
     * where Key - User's id and Value - User.
     * @param list Users
     * @return HashMap key-id, value-User
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }
}

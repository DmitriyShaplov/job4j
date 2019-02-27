package ru.job4j.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return new HashMap<>(list.stream().collect(Collectors.toMap(User::getId, Function.identity())));
    }
}

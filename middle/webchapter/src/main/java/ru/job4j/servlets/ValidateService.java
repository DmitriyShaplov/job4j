package ru.job4j.servlets;

import java.util.List;

/**
 * Request processing logic.
 *
 * @author shaplov
 * @since 04.06.2019
 */
public class ValidateService implements Validate {

    private static final ValidateService INSTANCE = new ValidateService();

    /**
     * Store instance.
     */
    private final Store logic = MemoryStore.getInstance();

    /**
     * Private Constructor.
     */
    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private boolean validateUser(User user) {
        var res = true;
        if (user.getLogin() == null) {
            res = false;
        } else if (!user.getEmail().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
            res = false;
        }
        return res;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        if (validateUser(user)) {
            logic.add(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        if (validateUser(user)) {
            logic.update(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user) {
        return logic.delete(user);
    }

    @Override
    public List<User> findAll() {
        return logic.findAll();
    }

    @Override
    public User findById(User user) {
        return logic.findById(user);
    }
}

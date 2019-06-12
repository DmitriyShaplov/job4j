package ru.job4j.servlets;

import ru.job4j.servlets.exceptions.InvalidPassword;

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
    private final Store logic = DbStore.getInstance();

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
        } else if (!user.getPassword().matches("^\\w{3,20}")) {
            throw new InvalidPassword("Invalid password");
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
    public boolean updateRole(User user) {
        return logic.updateRole(user);
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

    @Override
    public User findByLogin(User user) {
        return logic.findByLogin(user);
    }

    @Override
    public boolean isCredential(String login, String password) {
        return logic.isCredential(login, password);
    }
}

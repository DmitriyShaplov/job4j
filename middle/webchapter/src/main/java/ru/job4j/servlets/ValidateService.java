package ru.job4j.servlets;

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

    private String validateUser(User user) {
        String res = "ok";
        if (user.getLogin() == null) {
            res = "You should specify login.";
        } else if (!user.getEmail().matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")) {
            res = "Incorrect email.";
        }
        return res;
    }

    @Override
    public String add(User user) {
        String res = validateUser(user);
        return "ok".equals(res) ? String.format("User added. Id = %s.", logic.add(user)) : res;
    }

    @Override
    public String update(User user) {
        String res = validateUser(user);
        return "ok".equals(res) ? (logic.update(user)
                ? String.format("User id=%s updated successfully.", user.getId())
                : "There is no user with specified id") : res;
    }

    @Override
    public String delete(User user) {
        return logic.delete(user)
                ? String.format("User id=%s deleted successfully.", user.getId())
                : "There is no user with specified id";
    }

    @Override
    public String findAll() {
        return logic.findAll().toString();
    }

    @Override
    public String findById(User user) {
        User res = logic.findById(user);
        return res != null ? res.toString() : "There is no user with specified id";
    }
}

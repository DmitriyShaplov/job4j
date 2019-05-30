package ru.job4j.mailing;

/**
 * @author shaplov
 * @since 22.05.2019
 */
public class User {

    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String username() {
        return username;
    }

    public String email() {
        return email;
    }
}

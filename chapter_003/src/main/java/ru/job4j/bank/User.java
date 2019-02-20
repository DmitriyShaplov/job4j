package ru.job4j.bank;

import java.util.Objects;

/**
 * Bank Account holders.
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class User {

    private String name;
    private String passport;

    /**
     * Constructor
     * @param name User's name
     * @param passport User's passport
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passport);
    }
}

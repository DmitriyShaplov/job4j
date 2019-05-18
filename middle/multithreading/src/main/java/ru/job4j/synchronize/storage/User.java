package ru.job4j.synchronize.storage;

import java.util.Objects;

/**
 * @author shaplov
 * @since 17.05.2019
 */
public class User {

    private int id;

    /**
     * User's money.
     */
    private int amount;

    /**
     * Constructor.
     * @param id User id.
     * @param amount User start amount.
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Transfers amount from this User to another.
     * @param target target User.
     * @param value transfer amount.
     * @return success.
     */
    boolean transfer(User target, int value) {
        boolean success = false;
        if (value > 0 && this.amount >= value && target != null) {
            this.amount -= value;
            target.amount += value;
            success = true;
        }
        return  success;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
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
        return id == user.id
                && amount == user.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}

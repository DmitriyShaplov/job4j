package ru.job4j.servlets;

/**
 * Roles in system.
 *
 * @author shaplov
 * @since 12.06.2019
 */
public enum Role {
    ADMIN(3), MODER(2), USER(1);

    private final int priority;

    public int getPriority() {
        return priority;
    }

    Role(int priority) {
        this.priority = priority;
    }
}

package ru.shaplov.job4j.patterns.chainofresponsibility.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Модель пользователя ресурса.
 */
@Getter
@Builder
public class User {

    private int age;
    private boolean isBanned;
    private boolean isAdmin;

    public User(int age, boolean isBanned, boolean isAdmin) {
        this.age = age;
        this.isBanned = isBanned;
        this.isAdmin = isAdmin;
    }

    public User() {
    }
}

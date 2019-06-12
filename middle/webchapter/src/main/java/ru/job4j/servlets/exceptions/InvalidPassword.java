package ru.job4j.servlets.exceptions;

/**
 * @author shaplov
 * @since 12.06.2019
 */
public class InvalidPassword extends RuntimeException {

    public InvalidPassword(String message) {
        super(message);
    }

    public InvalidPassword() {
    }
}

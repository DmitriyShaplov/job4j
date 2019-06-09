package ru.job4j.servlets.exceptions;

/**
 * @author shaplov
 * @since 09.06.2019
 */
public class RepeatedLoginException extends RuntimeException {

    public RepeatedLoginException(String message) {
        super(message);
    }
}

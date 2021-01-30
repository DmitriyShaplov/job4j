package ru.shaplov.job4j.patterns.chainofresponsibility.exception;

/**
 * Ошибка, показывающая, что нет доступа к данному контенту.
 */
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}

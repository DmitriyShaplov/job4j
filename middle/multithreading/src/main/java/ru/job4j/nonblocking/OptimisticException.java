package ru.job4j.nonblocking;

/**
 * @author shaplov
 * @since 20.05.2019
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}

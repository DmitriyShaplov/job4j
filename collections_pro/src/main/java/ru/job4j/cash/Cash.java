package ru.job4j.cash;

/**
 * @author shaplov
 * @since 01.05.2019
 */
public interface Cash {

    /**
     * Gets cash string by key.
     * @param key String cash-key.
     * @return String cash value.
     */
    String getCash(String key);
}

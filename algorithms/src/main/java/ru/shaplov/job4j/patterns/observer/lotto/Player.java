package ru.shaplov.job4j.patterns.observer.lotto;

/**
 * @author shaplov
 * @since 06.01.2020
 */
public interface Player {

    /**
     * Marks number on the card.
     * @param number number on the keg.
     * @return if I won.
     */
    boolean markTheNumber(Integer number);
}

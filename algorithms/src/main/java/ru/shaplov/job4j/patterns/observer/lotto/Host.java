package ru.shaplov.job4j.patterns.observer.lotto;

/**
 * @author shaplov
 * @since 06.01.2020
 */
public interface Host {
    /**
     * Registers new player.
     */
    void subscribe(Player player);

    /**
     * Delete player from game.
     */
    void unsubscribe(Player player);

    /**
     * Take a keg and notify players.
     * If player returns true
     * invokes {@link this#weHaveWinner(Player)}.
     */
    void takeKeg();

    /**
     * Endgame.
     */
    void weHaveWinner(Player player);
}

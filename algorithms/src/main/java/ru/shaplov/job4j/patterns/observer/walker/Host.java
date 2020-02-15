package ru.shaplov.job4j.patterns.observer.walker;

import ru.shaplov.job4j.patterns.observer.lotto.Player;

/**
 * @author shaplov
 * @since 15.02.2020
 */
public interface Host {

    /**
     * Registers new player.
     */
    void subscribe(ru.shaplov.job4j.patterns.observer.lotto.Player player);

    /**
     * Delete player from game.
     */
    void unsubscribe(Player player);


    /**
     * Бесконечный цикл, поочередно синхронно запрашивает
     * у игроков - сколько у них выпало на кубике.
     * Делает проверки, перемещает игроков по карте.
     * Вызывает завершение игры, если игрок дошел до конца карты.
     * invokes {@link this#weHaveWinner(Player)}.
     */
    void game();

    /**
     * Endgame.
     */
    void weHaveWinner(Player player);
}

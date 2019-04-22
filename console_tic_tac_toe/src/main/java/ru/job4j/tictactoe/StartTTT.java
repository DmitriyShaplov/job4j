package ru.job4j.tictactoe;

import ru.job4j.tictactoe.console.ConsoleBoardCreator;
import ru.job4j.tictactoe.console.ConsoleInput;
import ru.job4j.tictactoe.console.ConsolePainter;
import ru.job4j.tictactoe.logics.SimpleLogic;
import ru.job4j.tictactoe.logics.SimpleLogicAI;

/**
 * @author shaplov
 * @since 22.04.2019
 */
public class StartTTT {

    /**
     * Main method of console tic-tac-toe.
     */
    public static void main(String[] args) {
        Game game = new SimpleGame(
                new ConsoleBoardCreator(),
                new ConsoleInput(System.in, System.out),
                new SimpleLogic(),
                new SimpleLogicAI(),
                new ConsolePainter(System.out)
        );
        game.start();
    }
}

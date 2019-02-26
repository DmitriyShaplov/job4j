package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version $Id$
 * @since 27.12.2018
 */
public class StartUI {

    private final Input input;
    private final Tracker tracker;
    private final Consumer<String> output;

    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Main program loop
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        List<Integer> range = new ArrayList<>();
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range.add(i);
        }
        do {
            menu.show();
        } while (menu.select(input.ask("Please, enter menu item number : ", range)));
    }

    /**
     * Run program
     * @param args cmd args.
     */
    public static void main(String[] args) {
        new StartUI(
                new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println
        ).init();
    }
}

package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * @version $Id$
 * @since 27.12.2018
 */
public class StartUI {

    static final String ADD = "0";
    static final String SHOW_ALL = "1";
    static final String EDIT = "2";
    static final String DELETE = "3";
    static final String FIND_BY_ID = "4";
    static final String FIND_BY_NAME = "5";
    static final String EXIT = "6";

    private final Input input;
    private final Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Main program loop
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
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
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}

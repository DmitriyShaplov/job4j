package ru.job4j.tracker;

import java.util.List;

/**
 * @author shaplov
 * @version $Id$
 * @since 19.01.2019
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, List<Integer> range) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                valid = true;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Enter valid data again.");
            }
        } while (!valid);
        return value;
    }
}

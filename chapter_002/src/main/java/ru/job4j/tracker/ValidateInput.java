package ru.job4j.tracker;

import java.util.List;

public class ValidateInput extends ConsoleInput {

    public int ask(String question, List<Integer> range) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
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

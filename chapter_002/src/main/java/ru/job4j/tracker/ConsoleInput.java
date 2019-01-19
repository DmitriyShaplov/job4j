package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author shaplov
 * @version $Id$
 */
public class ConsoleInput implements Input {

    private List<Validate> validates = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private void fillValidates() {
        this.validates.add(new Validate() {
            public void validate(int key, List<Integer> range) {
                if (ConsoleInput.this.isInRange(key, range)) {
                    throw new MenuOutException("Out of menu range.");
                }
            }
        });
    }

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Check if there is a key in range
     * @param key value
     * @param range list
     * @return result
     */
    private boolean isInRange(int key, List<Integer> range) {
        boolean exists = false;
        for (int num : range) {
            if (key == num) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public int ask(String question, List<Integer> range) {
         int key = Integer.valueOf(ask(question));
         for (Validate val : validates) {
             val.validate(key, range);
         }
         return key;
    }
}

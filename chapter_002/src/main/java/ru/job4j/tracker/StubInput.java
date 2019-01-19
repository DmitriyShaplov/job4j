package ru.job4j.tracker;

import java.util.List;

/**
 * Class for emulation user's input
 * @author shaplov
 */
public class StubInput implements Input {
    private final String[] value;

    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    /**
     * @return user's answers
     */
    public String ask(String question) {
        return this.value[this.position++];
    }

    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.value[this.position++]);
        boolean exists = false;
        for (int num : range) {
            if (key == num) {
                exists = true;
                break;
            }
        }
        if (exists) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}

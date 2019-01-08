package ru.job4j.tracker;

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
}

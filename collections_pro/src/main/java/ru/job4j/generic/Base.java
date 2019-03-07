package ru.job4j.generic;

/**
 * @author shaplov
 * @since 07.03.2019
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

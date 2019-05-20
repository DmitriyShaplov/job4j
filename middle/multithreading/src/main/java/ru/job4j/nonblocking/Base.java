package ru.job4j.nonblocking;

import java.util.Objects;

/**
 * @author shaplov
 * @since 20.05.2019
 */
public class Base {

    private final int id;
    private volatile int version = 0;

    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int version() {
        return version;
    }

    public synchronized void increment() {
        version++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && version == base.version
                && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, name);
    }
}

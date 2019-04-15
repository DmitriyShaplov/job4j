package ru.job4j.store;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Entry {
    private int value;

    public Entry() {
    }

    public Entry(int value) {
        this.value = value;
    }

    public int getField() {
        return value;
    }

    public void setField(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return value == entry.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
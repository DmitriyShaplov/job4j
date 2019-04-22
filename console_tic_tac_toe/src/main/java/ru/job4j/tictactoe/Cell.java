package ru.job4j.tictactoe;

public interface Cell<T> {

    boolean hasMarkX();

    boolean hasMarkO();

    boolean isEmpty();

    void take(boolean markX);

    void draw(T brush);
}

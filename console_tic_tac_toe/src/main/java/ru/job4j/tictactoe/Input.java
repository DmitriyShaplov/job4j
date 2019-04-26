package ru.job4j.tictactoe;

public interface Input {

    String getInitCommand();

    int getNewSize();

    String getSide();

    String validateInput(int size);
}

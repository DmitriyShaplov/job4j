package ru.job4j.chess;

import java.io.IOException;

public class ImpossibleMoveException extends IOException {

    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}

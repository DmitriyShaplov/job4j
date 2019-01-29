package ru.job4j.chess;

import java.io.IOException;

public class FigureNotFoundException extends IOException {

    public FigureNotFoundException(String msg) {
        super(msg);
    }
}

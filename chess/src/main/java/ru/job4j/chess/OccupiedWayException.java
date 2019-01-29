package ru.job4j.chess;

import java.io.IOException;

public class OccupiedWayException extends IOException {

    public OccupiedWayException(String msg) {
        super(msg);
    }
}

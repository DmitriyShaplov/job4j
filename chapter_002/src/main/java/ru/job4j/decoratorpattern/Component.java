package ru.job4j.decoratorpattern;

import java.util.ArrayList;

public interface Component {
    ArrayList<String> getDescription();
    double getCost();
}

package ru.job4j.tracker;

import java.util.List;

public interface Input {

    String ask(String question);

    String ask(String question, List<Integer> range);
}

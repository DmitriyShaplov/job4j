package ru.job4j.decoratorpattern;

import java.util.ArrayList;

public class Computer implements Component {
    @Override
    public ArrayList<String> getDescription() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("My computer");
        return arrayList;
    }

    @Override
    public double getCost() {
        return 100000;
    }
}

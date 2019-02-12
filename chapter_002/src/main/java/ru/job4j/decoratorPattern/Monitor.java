package ru.job4j.decoratorPattern;

import java.util.ArrayList;

public class Monitor extends ComponentDecorator {
    public Monitor(Component component) {
        super(component);
    }

    @Override
    public ArrayList<String> getDescription() {
        ArrayList<String> arrayList = wrapped.getDescription();
        arrayList.add("AOC G2460 144Hz");
        return arrayList;
    }

    @Override
    public double getCost() {
        return wrapped.getCost() + 15000;
    }
}

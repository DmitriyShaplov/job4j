package ru.job4j.decoratorpattern;

import java.util.ArrayList;

public class Mouse extends ComponentDecorator {
    public Mouse(Component component) {
        super(component);
    }

    @Override
    public ArrayList<String> getDescription() {
        ArrayList<String> arrayList = wrapped.getDescription();
        arrayList.add("Zowie FK2");
        return arrayList;
    }

    @Override
    public double getCost() {
        return wrapped.getCost() + 4650;
    }
}

package ru.job4j.decoratorpattern;

import java.util.ArrayList;

public class Keyboard extends ComponentDecorator {
    public Keyboard(Component component) {
        super(component);
    }

    @Override
    public ArrayList<String> getDescription() {
        ArrayList<String> arrayList = wrapped.getDescription();
        arrayList.add("Razer Chroma X");
        return arrayList;
    }

    @Override
    public double getCost() {
        return wrapped.getCost() + 11560;
    }
}

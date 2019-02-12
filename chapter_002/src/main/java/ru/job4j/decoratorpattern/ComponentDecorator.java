package ru.job4j.decoratorpattern;

import java.util.ArrayList;

public abstract class ComponentDecorator implements Component {
    protected Component wrapped;

    public ComponentDecorator(Component component) {
        this.wrapped = component;
    }

    @Override
    public abstract ArrayList<String> getDescription();


    @Override
    public abstract double getCost();
}

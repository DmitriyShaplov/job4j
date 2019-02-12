package ru.job4j.decoratorPattern;

public class Main {
    public static void main(String[] args) {
        Component computer = new Computer();
        computer = new Keyboard(computer);
        computer = new Mouse(computer);
        computer = new Monitor(computer);
        System.out.println("Order for my computer");
        for (String part : computer.getDescription()) {
            System.out.println("Component: " + part);
        }
        System.out.println("Total cost: " + computer.getCost());
    }
}

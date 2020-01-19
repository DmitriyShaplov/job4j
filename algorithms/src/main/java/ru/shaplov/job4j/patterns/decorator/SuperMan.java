package ru.shaplov.job4j.patterns.decorator;

/**
 * @author shaplov
 * @since 19.01.2020
 */
public class SuperMan implements Hero {
    private final Hero base;

    public SuperMan(Hero base) {
        this.base = base;
    }

    @Override
    public void jump() {
        this.base.jump();
    }

    public void fly() {
    }
}

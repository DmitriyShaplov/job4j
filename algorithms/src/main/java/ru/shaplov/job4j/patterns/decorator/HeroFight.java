package ru.shaplov.job4j.patterns.decorator;

/**
 * @author shaplov
 * @since 19.01.2020
 */
public class HeroFight implements Hero {
    private final Hero base;

    public HeroFight(Hero base) {
        this.base = base;
    }

    @Override
    public void jump() {
        this.base.jump();
        this.base.jump();
    }

    public void weapon() {
    }
}

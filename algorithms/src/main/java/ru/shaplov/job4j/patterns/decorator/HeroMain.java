package ru.shaplov.job4j.patterns.decorator;

/**
 * @author shaplov
 * @since 19.01.2020
 */
public class HeroMain {
    public static void main(String[] args) {
        Hero man = new SuperMan(new HeroFight(new Human()));
    }
}

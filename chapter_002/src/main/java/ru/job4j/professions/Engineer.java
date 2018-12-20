package ru.job4j.professions;

import ru.job4j.professions.results.HouseType;
import ru.job4j.professions.things.House;

public class Engineer extends Profession {
    public HouseType build(House house) {
        return new HouseType();
    }
}

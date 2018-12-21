package ru.job4j.professions;

import ru.job4j.professions.results.Diagnose;
import ru.job4j.professions.things.Pacient;

public class Doctor extends Profession {
//    public Doctor(String name) {
//        this.name = name;
//        this.profession = "Doctor";
//    }
    public Diagnose heal(Pacient pacient) {
        return new Diagnose();
    }
}

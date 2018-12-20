package ru.job4j.professions;

import ru.job4j.professions.results.Degree;
import ru.job4j.professions.things.Student;

public class Teacher extends Profession {
    public Degree teach(Student student) {
        return new Degree();
    }
}

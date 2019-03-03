package ru.job4j.streamapi;

/**
 * Student with score field.
 * @author shaplov
 * @since 03.03.2019
 */
public class Student {

    private String surname;
    private int score;

    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getSurname() {
        return surname;
    }
}

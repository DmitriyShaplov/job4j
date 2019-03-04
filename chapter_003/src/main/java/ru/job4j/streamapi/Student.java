package ru.job4j.streamapi;

/**
 * Student with score field.
 * @author shaplovd
 * @since 03.03.2019
 */
public class Student {

    //this 1 3
    private String surname;
    private int score;

    public Student(String surname, int score) {
        this.surname = surname;
        this.score = score;
    }

    public String getSurname() {
        return surname;
    }

    public int getScore() {
        return score;
    }
}

package ru.job4j.streamAPI;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SchoolTest {

    private List<Student> students = List.of(
            new Student(10),
            new Student(70),
            new Student(69),
            new Student(35),
            new Student(99),
            new Student(48),
            new Student(54)
    );

    @Test
    public void whenGet10AClassList() {
        School school = new School();
        List<Student> list = school.collect(students,
                v -> v.getScore() >= 70 && v.getScore() <= 100);
        String resString = list.stream()
                .map(Student::getScore)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        boolean result = resString.contains("99")
                && resString.contains("70");
        assertThat(list.size(), is(2));
        assertThat(result, is(true));
    }

    @Test
    public void whenGet10BClassList() {
        School school = new School();
        List<Student> list = school.collect(students,
                v -> v.getScore() >= 50 && v.getScore() < 70);
        String resString = list.stream()
                .map(Student::getScore)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        boolean result = resString.contains("54")
                && resString.contains("69");
        assertThat(list.size(), is(2));
        assertThat(result, is(true));
    }

    @Test
    public void whenGet10VClassList() {
        School school = new School();
        List<Student> list = school.collect(students,
                v -> v.getScore() > 0 && v.getScore() < 50);
        String resString = list.stream()
                .map(Student::getScore)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        boolean result = resString.contains("10")
                && resString.contains("35")
                && resString.contains("48");
        assertThat(list.size(), is(3));
        assertThat(result, is(true));
    }
}

package ru.job4j.streamapi;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test School
 */
public class SchoolTest {

    private final List<Student> students = List.of(
            new Student("test0", 10),
            new Student("test1", 70),
            new Student("test2", 69),
            new Student("test3", 35),
            new Student("test4", 99),
            new Student("test5", 48),
            new Student("test6", 54)
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


    @Test
    public void whenGetMapOfStudents() {
        School school = new School();
        var list = school.toMap(students);
        assertThat(list.get("test3").getScore(), is(35));
    }
}

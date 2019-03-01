package ru.job4j.lambda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentTest {
    @Test
    public void whenLevelOfThenSortedListWithBoundScope() {
        List<Student> students = new ArrayList<>();
        Student student = new Student("test", 1);
        students.add(null);
        students.add(student);
        students.add(new Student("test1", 3));
        students.add(new Student("test2", 6));
        students.add(null);
        students.add(new Student("test3", 5));
        students.add(new Student("test4", 4));
        students.add(null);
        students.add(new Student("test5", 5));
        List<Student> listRes = student.levelOf(students, 3);
        String result = listRes.stream()
                .map(v -> String.valueOf(v.getScope()))
                .collect(Collectors.joining());
        String expect = "6554";
        assertThat(listRes.size(), is(4));
        assertThat(result, is(expect));
    }
}

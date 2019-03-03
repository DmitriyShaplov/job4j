package ru.job4j.streamAPI;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class School.
 * @author shaplov
 * @since 03.03.2019
 */
public class School {

    /**
     * Method for getting List of Students filtered by score range.
     * @param students List of Students
     * @param predicate predicate with needed score
     * @return filtered List of Students
     */
    public List<Student> collect(List<Student> students, Predicate<Student> predicate) {
        return students.stream().filter(predicate).collect(Collectors.toList());
    }
}

package ru.job4j.lambda;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version 0.1
 * @since 01.03.2019
 * @author shaplov
 */
public class Student implements Comparable<Student> {

    private String name;
    private int scope;

    /**
     * Constructor
     * @param name name
     * @param scope scope
     */
    public Student(String name, int scope) {
        this.name = name;
        this.scope = scope;
    }

    public String getName() {
        return name;
    }

    public int getScope() {
        return scope;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(o.scope, this.scope);
    }

    /**
     *
     * @param students List of Students
     * @param bound threshold scope
     * @return Sorted List of Students with
     * scopes higher the bound
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().flatMap(Stream::ofNullable)
                .sorted()
                .takeWhile(v -> v.scope > bound)
                .collect(Collectors.toList());
    }
}

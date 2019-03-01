package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Returns a List of Persons containing key in any fields;
     * @param key search key
     * @return List of suitable Persons
     */
    public List<Person> find(String key) {
        return this.persons.stream()
                .filter(
                        p -> p.getName().contains(key)
                        || p.getSurname().contains(key)
                        || p.getPhone().contains(key)
                        || p.getAddress().contains(key)
                ).collect(Collectors.toList());
    }
}

package ru.job4j.search;

/**
 * Class for Users.
 * Contains id, name, city.
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class User implements Comparable<User> {
    private int id;
    private String name;
    private String city;
    private int age;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age, o.age);
    }
}

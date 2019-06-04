package ru.job4j.servlets;

/**
 * @author shaplov
 * @since 04.06.2019
 */
public interface Validate {

    String add(User user);

    String update(User user);

    String delete(User user);

    String findAll();

    String findById(User user);
}

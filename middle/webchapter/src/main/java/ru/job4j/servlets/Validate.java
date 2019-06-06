package ru.job4j.servlets;

import java.util.List;

/**
 * @author shaplov
 * @since 04.06.2019
 */
public interface Validate {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(User user);
}

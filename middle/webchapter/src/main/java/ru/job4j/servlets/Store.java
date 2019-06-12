package ru.job4j.servlets;

import java.util.List;

/**
 * @author shaplov
 * @since 04.06.2019
 */
public interface Store {

    User add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(User user);

    User findByLogin(User user);

    boolean isCredential(String login, String password);

    boolean updateRole(User user);
}

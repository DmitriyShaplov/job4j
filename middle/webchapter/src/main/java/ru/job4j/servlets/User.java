package ru.job4j.servlets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

/**
 * Encapsulating User model.
 *
 * @author shaplov
 * @since 04.06.2019
 */
public class User {

    private String id;
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;
    private String password;
    private Role role;
    private String country;
    private String city;

    public User(String id, String name, String login, String email, LocalDate createDate, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.password = password;
        this.role = role;
    }

    public User(String id) {
        this.id = id;
    }

    public User(String id, String login) {
        this.id = id;
        this.login = login;
    }

    public User(String id, Role role) {
        this.id = id;
        this.role = role;
    }

    public User(String id, String name, String login, String email, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String login, String email, String password, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate.format(DateTimeFormatter.ofPattern("yyyy MMMM dd").withLocale(Locale.US));
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public int getPriority() {
        return this.role.getPriority();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, email);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + '}';
    }

}

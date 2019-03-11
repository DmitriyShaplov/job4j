package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author shaplov
 * @since 11.03.2019
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return children == user.children &&
                Objects.equals(name, user.name) &&
                Objects.equals(birthday, user.birthday);
    }

//    @Override
//    public int hashCode() {
//        int result = 1;
//        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
//        result = 31 * result + Integer.valueOf(this.children).hashCode();
//        result = 31 * result + (this.birthday == null ? 0 : this.birthday.hashCode());
//        return result;
//    }
}

package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    @Test
    public void whenPrintUsersWithoutOverridingEqualsAndHashCode() {
        User first = new User("Dmitriy", 0, new GregorianCalendar(1993, Calendar.JUNE, 5));
        User second = new User("Dmitriy", 0, new GregorianCalendar(1993, Calendar.JUNE, 5));
        Map<User, Object> map = new HashMap<>();
        map.put(first, "first");
        map.put(second, "second");
        System.out.println(map);
    }
}
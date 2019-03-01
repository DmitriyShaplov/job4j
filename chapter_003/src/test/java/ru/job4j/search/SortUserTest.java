package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SortUser.
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {
    @Test
    public void whenSortUsersThenTreeSet() {
        SortUser sortUser = new SortUser();
        List<User> list = List.of(
                new User("Dmitriy", 25),
                new User("test1", 26),
                new User("test2", 22),
                new User("test3", 20)
        );
        Set<User> setUsers = sortUser.sort(list);
        String result = setUsers.stream()
                .flatMap(Stream::ofNullable)
                .map(User::getName)
                .collect(Collectors.joining());
        assertThat(result, is("test3test2Dmitriytest1"));
    }

    @Test
    public void whenSortUsersByNamesLength() {
        SortUser sortUser = new SortUser();
        User user1 = new User("Сергей", 25);
        User user2 = new User("Владимир", 30);
        User user3 = new User("Дмитрий", 20);
        User user4 = new User("Иван", 25);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<User> resultedList = sortUser.sortNameLength(list);
        List<User> expect = List.of(user4, user1, user3, user2);
        boolean result = resultedList.equals(expect);
        assertThat(result, is(true));
    }

    @Test
    public void whenSortUsersByNameAndAge() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User user1 = new User("Сергей", 25);
        User user2 = new User("Иван", 30);
        User user3 = new User("Сергей", 20);
        User user4 = new User("Иван", 25);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<User> resultedList = sortUser.sortByAllFields(list);
        List<User> expect = List.of(user4, user2, user3, user1);
        boolean result = resultedList.equals(expect);
        assertThat(result, is(true));
    }
}

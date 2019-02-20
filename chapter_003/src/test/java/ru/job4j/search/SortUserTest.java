package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<User> list = new ArrayList<>();
        list.add(new User("Dmitriy", 25));
        list.add(new User("test1", 26));
        list.add(new User("test2", 22));
        list.add(new User("test3", 20));
        Set<User> setUsers = sortUser.sort(list);
        StringBuilder sb = new StringBuilder();
        for (User user : setUsers) {
            sb.append(user.getName());
        }
        assertThat(sb.toString(), is("test3test2Dmitriytest1"));
    }

    @Test
    public void whenSortUsersByNamesLength() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User user1 = new User("Сергей", 25);
        User user2 = new User("Владимир", 30);
        User user3 = new User("Дмитрий", 20);
        User user4 = new User("Иван", 25);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<User> resultedList = sortUser.sortNameLength(list);
        List<User> expect = new ArrayList<>();
        expect.add(user4);
        expect.add(user1);
        expect.add(user3);
        expect.add(user2);
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
        List<User> expect = new ArrayList<>();
        expect.add(user4);
        expect.add(user2);
        expect.add(user3);
        expect.add(user1);
        boolean result = resultedList.equals(expect);
        assertThat(result, is(true));
    }
}

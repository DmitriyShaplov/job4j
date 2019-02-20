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
}

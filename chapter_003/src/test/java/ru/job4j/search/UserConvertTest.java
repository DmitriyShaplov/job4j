package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author shaplov
 * @version $Id$
 * since 0.1
 */
public class UserConvertTest {
    @Test
    public void whenConvertThenHashMap() {
        User user = new User(100, "Dmitriy", "Odincovo");
        List<User> users = List.of(user);
        UserConvert converter = new UserConvert();
        HashMap<Integer, User> result = converter.process(users);
        assertThat(result.get(100), is(user));
    }
}

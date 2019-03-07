package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class StoreTest {

    @Test
    public void whenAddAndGetUserToFromUserStore() {
        UserStore userStore = new UserStore(10);
        userStore.add(new User("test"));
        User user = userStore.findById("test");
        String result = user.getId();
        assertThat(result, is("test"));
    }

    @Test
    public void whenReplaceUserThenReplaced() {
        UserStore userStore = new UserStore(10);
        userStore.add(new User("test"));
        boolean bRes = userStore.replace("test", new User("replaced"));
        String result = userStore.findById("replaced").getId();
        assertThat(bRes, is(true));
        assertThat(result, is("replaced"));
    }

    @Test
    public void whenReplaceUserThenDidNotReplace() {
        UserStore userStore = new UserStore(10);
        userStore.add(new User("test"));
        var bRes = userStore.replace("nothing", new User("replaced"));
        var result = userStore.findById("replaced");
        assertThat(bRes, is(false));
        assertNull(result);
    }

    @Test
    public void whenDeleteRoleThenNoRole() {
        RoleStore roleStore = new RoleStore(10);
        roleStore.add(new Role("test"));
        roleStore.add(new Role("test2"));
        var bRes = roleStore.delete("test");
        var result = roleStore.findById("test");
        assertThat(bRes, is(true));
        assertNull(result);
    }
}
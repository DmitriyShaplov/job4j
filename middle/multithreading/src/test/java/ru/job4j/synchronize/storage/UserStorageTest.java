package ru.job4j.synchronize.storage;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenCreateUserThenAddUserToStorageTwiceResultFirstTrueSecondFalse() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        var res1 = storage.add(user1);
        var res2 = storage.add(user1);
        assertTrue(res1);
        assertFalse(res2);
    }

    @Test
    public void whenCreate2UsersThenTransferMoneyResultChangedAmount() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);
        var res = storage.transfer(1, 2, 50);
        assertTrue(res);
        assertThat(user1.getAmount(), is(50));
        assertThat(user2.getAmount(), is(250));
    }

    @Test
    public void whenCreate2UsersThenDeleteOneResultFalse() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        storage.add(user1);
        storage.add(user2);
        var resDel = storage.delete(user1);
        var resTrans = storage.transfer(1, 2, 50);
        assertTrue(resDel);
        assertFalse(resTrans);
        assertThat(user2.getAmount(), is(200));
    }

    @Test
    public void whenAddOneUserThenReplaceItResultNewValue() {
        UserStorage storage = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        User userNew = new User(1, 50);
        storage.add(user1);
        storage.add(user2);
        var resRep = storage.update(userNew);
        var res = storage.transfer(1, 2, 50);
        assertTrue(resRep);
        assertTrue(res);
        assertThat(user1.getAmount(), is(100));
        assertThat(userNew.getAmount(), is(0));
    }
}
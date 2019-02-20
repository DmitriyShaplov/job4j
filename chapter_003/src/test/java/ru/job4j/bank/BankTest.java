package ru.job4j.bank;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void whenAddUserMapSizeOne() {
        Bank bank = new Bank();
        User user = new User("Dmitriy", "123456");
        bank.addUser(user);
        User resUser = bank.getUser("123456");
        assertThat(resUser.getName(), is("Dmitriy"));
    }

    @Test
    public void whenDeleteUserMapSizeZero() {
        Bank bank = new Bank();
        User user = new User("Dmitriy", "123456");
        bank.addUser(user);
        bank.deleteUser(user);
        assertNull(bank.getUser("123456"));
    }

    @Test
    public void whenAddAccountToUser() {
        Bank bank = new Bank();
        User user = new User("Dmitriy", "123456");
        Account account = new Account(10000, "testReq");
        bank.addUser(user);
        bank.addAccountToUser("123456", account);
        List<Account> list = bank.getUserAccounts("123456");
        assertThat(list.get(0).getRequisites(), is("testReq"));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        Bank bank = new Bank();
        User user = new User("Dmitriy", "123456");
        Account account = new Account(10000, "testReq");
        bank.addAccountToUser("123456", account);
        bank.deleteAccountFromUser("123456", account);
        List<Account> list = bank.getUserAccounts("123456");
        assertThat(list.isEmpty(), is(true));
    }

    @Test
    public void whenTransferMoneyFromOneUserToAnother() {
        Bank bank = new Bank();
        User srcUser = new User("Dmitriy", "123456");
        User desUser = new User("Artem", "55555");
        Account srcAcc = new Account(10000, "srcTest");
        Account desAcc = new Account(20000, "desTest");
        bank.addUser(srcUser);
        bank.addUser(desUser);
        bank.addAccountToUser("123456", srcAcc);
        bank.addAccountToUser("55555", desAcc);
        bank.transferMoney("123456", "srcTest", "55555", "desTest", 5000);
        List<Account> listSrc = bank.getUserAccounts("123456");
        List<Account> listDes = bank.getUserAccounts("55555");
        assertThat(listSrc.get(0).getValue(), is(5000.0));
        assertThat(listDes.get(0).getValue(), is(25000.0));
    }
}

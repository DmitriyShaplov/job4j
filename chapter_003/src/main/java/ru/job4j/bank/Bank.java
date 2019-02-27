package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The class represents a bank of users with accounts
 * @author shaplov
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    private Map<User, List<Account>> userListMap = new HashMap<>();

    /**
     * Add User
     * @param user User
     */
    public void addUser(User user) {
        if (user != null) {
            this.userListMap.putIfAbsent(user, new ArrayList<>());
        }
    }

    /**
     * Delete User
     * @param user User
     */
    public void deleteUser(User user) {
        this.userListMap.remove(user);
    }

    /**
     * Add Account to User
     * @param passport User's passport
     * @param account Account
     */
    public void addAccountToUser(String passport, Account account) {
        if (account == null || passport == null) {
            return;
        }
        this.userListMap.entrySet().stream()
                .filter(userListEntry -> userListEntry.getKey().getPassport().equals(passport))
                .filter(userListEntry -> !userListEntry.getValue().contains(account))
                .limit(1)
                .forEach(userListEntry -> userListEntry.getValue().add(account));
//        for (User user : this.userListMap.keySet()) {
//            if (user.getPassport().equals(passport)) {
//                if (!this.userListMap.get(user).contains(account)) {
//                    this.userListMap.get(user).add(account);
//                }
//                break;
//            }
//        }
    }

    /**
     * Delete Account from User
     * @param passport User's passport
     * @param account Account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (account == null || passport == null) {
            return;
        }
        this.userListMap.entrySet().stream()
                .filter(userListEntry -> userListEntry.getKey().getPassport().equals(passport))
                .map(Map.Entry::getValue)
                .limit(1)
                .forEach(
                        list -> {
                            list.removeIf(
                                    acc -> acc.equals(account)
                            );
                        }
                );

//        for (User user : this.userListMap.keySet()) {
//            if (user.getPassport().equals(passport)) {
//                this.userListMap.get(user).remove(account);
//                break;
//            }
//        }
    }

    /**
     * Get List of User's Accounts
     * @param passport User's passport
     * @return List of User's Accounts
     */
    public List<Account> getUserAccounts(String passport) {
        if (passport == null) {
            return new ArrayList<>();
        }
//        return this.userListMap.entrySet().stream()
//                .filter(
//                        userListEntry -> userListEntry.getKey().getPassport().equals(passport)
//                ).flatMap(
//                        userListEntry -> userListEntry.getValue().stream()
//                ).collect(Collectors.toList());
        return this.userListMap.entrySet().stream()
                .filter(
                        userListEntry -> userListEntry.getKey().getPassport().equals(passport)
                ).map(Map.Entry::getValue).findAny().orElse(new ArrayList<>());
//        List<Account> accountList = new ArrayList<>();
//        for (User user : this.userListMap.keySet()) {
//            if (user.getPassport().equals(passport)) {
//                accountList = this.userListMap.get(user);
//                break;
//            }
//        }
//        return accountList;
    }

    /**
     * Transfer money from one existing account in this bank to another.
     * @param srcPassport source User's passport
     * @param srcRequisite source Account's requisite
     * @param desPassport destination User's passport
     * @param desRequisite destination Account's requisite
     * @param amount amount of money in double
     * @return result
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String desPassport, String desRequisite, double amount) {
        User userSrc = getUser(srcPassport);
        User userDes = getUser(desPassport);
        Account accountSrc = null;
        Account accountDes = null;
        if (userSrc != null) {
            accountSrc = getAccount(userSrc, srcRequisite);
        }
        if (userDes != null) {
            accountDes = getAccount(userDes, desRequisite);
        }
        return accountSrc != null && accountDes != null && accountSrc.transfer(accountDes, amount);
    }

    /**
     * Find User by passport
     * @param passport User's passport
     * @return User or null
     */
    public User getUser(String passport) {
        return this.userListMap.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findAny().orElse(null);
//        User result = null;
//        for (User user : this.userListMap.keySet()) {
//            if (user.getPassport().equals(passport)) {
//                result = user;
//            }
//        }
//        return result;
    }

    /**
     * Find Account by User and requisite
     * @param user User
     * @param requisite Account's requisite
     * @return Account or null
     */
    private Account getAccount(User user, String requisite) {
        List<Account> list = this.userListMap.get(user);
        if (list != null) {
            return list.stream().filter(account -> account.getRequisites().equals(requisite))
                    .findAny().orElse(null);
        }
        return null;
//        Account result = null;
//        for (Account account : this.userListMap.get(user)) {
//            if (account.getRequisites().equals(requisite)) {
//                result = account;
//            }
//        }
//        return result;
    }
}
